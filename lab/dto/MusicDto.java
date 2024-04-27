package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Информация о музыке")
public class MusicDto {

    @JsonProperty("id")
    @Schema(description = "ID музыки в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название музыки может содержать только буквы")
    @Schema(description = "Название музыки", example = "Скорбь")
    private String name;

    @JsonProperty("genre")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название жанра может содержать только буквы")
    @Schema(description = "Название жанра", example = "Рок")
    private String genre;

    @JsonProperty("style")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название стиля может содержать только буквы")
    @Schema(description = "Название стиль", example = "Жесткий")
    private String style;

    @JsonProperty("album")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название альбома может содержать только буквы")
    @Schema(description = "Название альбома", example = "Эщкере")
    private String album;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления  картины в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;


    @JsonProperty("author")
    @Schema(description = "Имя автора музыки",example = "Винсент Ван Гог")
    private String author;

    @JsonProperty("platform")
    @Schema(description = "Название платформы",example = "Spotify")
    private String platform;

}
