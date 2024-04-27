package ru.edu.penzgtu.lab.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.lab.dto.MusicDto;
import ru.edu.penzgtu.lab.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.entity.Platform;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.MusicRepository;
import ru.edu.penzgtu.lab.repo.PlatformRepository;
import ru.edu.penzgtu.lab.service.Mapper.MusicMapper;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final PlatformRepository platformRepository;

    public Music getByName(String name) {
        return musicRepository.findByName(name)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Not found by Name " + name));
    }
    public List<MusicDto> findAllMusics() {
        log.info("Найдены все существующие песни в БД"); return musicMapper.toListDto(musicRepository.findAll());
    }

    public MusicDto findMusicById(Long id) {
        log.info("Найдена музыка по id" + id);
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Музыка с id" + id + "не найдена"));
        return musicMapper.toDto(music);
    }

    public void saveMusic(MusicDto musicDto) {
        log.info("Песня сохранена");
        Music music = musicMapper.toEntity(musicDto);
        music.setLocalDateTime(LocalDateTime.now());
        musicRepository.save(music);

        Platform platform = platformRepository.findByName(musicDto.getPlatform()).get();

        music.getPlatforms().add(platform);
        musicRepository.save(music);
    }

    public void updateMusic(MusicDto newMusic) {
        log.info("Данные о музыки были обновлены");
        Music oldMusic = musicRepository.findById(newMusic.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Музыка не найдена"));

        oldMusic.setName(newMusic.getName());
        oldMusic.setGenre(newMusic.getGenre());
        oldMusic.setStyle(newMusic.getStyle());
        oldMusic.setAlbum(newMusic.getAlbum());

        musicRepository.save(oldMusic);
    }

    public void deleteMusicById(Long id) {
        log.info("Удалена музыка с id:" + id); musicRepository.deleteById(id);
    }
}