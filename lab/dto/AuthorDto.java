package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация об авторе")
public class AuthorDto {

    @JsonProperty("id")
    @Schema(description = "ID автора в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя автора может содержать только буквы и пробелы")
    @Schema(description = "Имя автора", example = "Антонио Вивальди")
    private String name;

    @JsonProperty("age")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Возраст автора может содержать только буквы и пробелы")
    @Schema(description = "Возраст автора", example = "237")
    private String age;

    @JsonProperty("pseudonym")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Псевдоним автора может содержать только буквы и пробелы")
    @Schema(description = "Псевдоним автора", example = "Эщкере")
    private String pseudonym;

    @JsonProperty("publisher")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя издателя может содержать только буквы и пробелы")
    @Schema(description = "Имя издателя", example = "vk music")
    private String publisher;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления автора в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("musics")
    @Size(min = 1,max = 44,
            message = "Количество названий музыки должно быть от 1 до 44")
    @Schema(description = "Названия песен автора")
    private List<String> musics;

    @JsonProperty("producer")
    @Schema(description = "Имя продюсера картины",example = "Винсент Ван Гог")
    private String producer;

    @JsonProperty("platform")
    @Schema(description = "Платформы на которых есть автор",example = "vk music")
    private String platform;
}