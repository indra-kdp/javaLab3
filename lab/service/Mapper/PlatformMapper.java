package ru.edu.penzgtu.lab.service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.PlatformDto;
import ru.edu.penzgtu.lab.entity.Music;
import ru.edu.penzgtu.lab.entity.Platform;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformMapper {
    public List<PlatformDto> toListDto(List<Platform> platforms) {
        return platforms.stream().map(this::toDto).toList();
    }

    public PlatformDto toDto(Platform platform) {
        return PlatformDto.builder()
                .id(platform.getId())
                .name(platform.getName())
                .country(platform.getCountry())
                .company(platform.getCompany())
                .catalog(platform.getCatalog())
                .localDateTime(platform.getLocalDateTime())
                .musicName(String.join(", ", platform.getMusics().stream().map(Music::getName).toList()))
                .build();
    }

    public Platform toEntity(PlatformDto platformDto) {
        Platform platform = new Platform();

        platform.setId(platformDto.getId());
        platform.setName(platformDto.getName());
        platform.setCountry(platformDto.getCountry());
        platform.setLocalDateTime(platformDto.getLocalDateTime());
        platform.setCatalog(platformDto.getCatalog());
        platform.setCompany(platformDto.getCompany());

        return platform;
    }
}
