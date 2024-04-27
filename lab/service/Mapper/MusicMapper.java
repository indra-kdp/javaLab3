package ru.edu.penzgtu.lab.service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.MusicDto;
import ru.edu.penzgtu.lab.entity.Music;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicMapper {
    public List<MusicDto> toListDto(List<Music> musics) {
        return musics.stream().map(this::toDto).toList();
    }

    public MusicDto toDto(Music music) {
        return MusicDto.builder()
                .id(music.getId())
                .name(music.getName())
                .genre(music.getGenre())
                .style(music.getStyle())
                .album(music.getAlbum())
                .localDateTime(music.getLocalDateTime())
                .build();
    }

    public Music toEntity(MusicDto musicDto) {
        Music music = new Music();

        music.setId(musicDto.getId());
        music.setName(musicDto.getName());
        music.setGenre(musicDto.getGenre());
        music.setLocalDateTime(musicDto.getLocalDateTime());
        music.setStyle(musicDto.getStyle());
        music.setAlbum(musicDto.getAlbum());

        return music;
    }
}
