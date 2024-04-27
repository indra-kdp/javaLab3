package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.penzgtu.lab.dto.PlatformDto;
import ru.edu.penzgtu.lab.entity.Music;
import ru.edu.penzgtu.lab.entity.Platform;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.PlatformRepository;
import ru.edu.penzgtu.lab.service.Mapper.PlatformMapper;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;
    private final MusicService musicService;

    public Platform getByName(String name) {
        return platformRepository.findByName(name)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Not found by Name " + name));
    }
    public List<PlatformDto> findAllPlatforms() {
        log.info("Найдены все существующие платформы в БД"); return platformMapper.toListDto(platformRepository.findAll());
    }

    public PlatformDto findPlatformById(Long id) {
        log.info("Найдена платформа по id" + id);
        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Платформа с id" + id + "не найдена"));
        return platformMapper.toDto(platform);
    }

    @Transactional
    public void savePlatform(PlatformDto platformDto) {
        log.info("Платформа сохранена");
        Platform platform = platformMapper.toEntity(platformDto);
        platform.setLocalDateTime(LocalDateTime.now());

        platformRepository.save(platform);

        Music music = musicService.getByName(platformDto.getMusicName());

        platform.getMusics().add(music);
        platformRepository.save(platform);
    }

    @Transactional
    public void updatePlatform(PlatformDto newPlatform) {
        log.info("Данные о платформе были обновлены");
        Platform oldPlatform = platformRepository.findById(newPlatform.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Платформа не найдена"));

        oldPlatform.setName(newPlatform.getName());
        oldPlatform.setCountry(newPlatform.getCountry());
        oldPlatform.setCompany(newPlatform.getCompany());
        oldPlatform.setCatalog(newPlatform.getCatalog());

        Music music = musicService.getByName(newPlatform.getMusicName());

        oldPlatform.getMusics().add(music);

        platformRepository.save(oldPlatform);
    }

    public void deletePlatformById(Long id) {
        log.info("Удалена платформа с id:" + id); platformRepository.deleteById(id);
    }
}
