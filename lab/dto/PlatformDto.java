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
@Schema(description = "Информация о платформе")
public class PlatformDto {
    @JsonProperty("id")
    @Schema(description = "ID платформы в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Название платформы в БД", example = "VK music")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название платформы может содержать только буквы")
    private String name;

    @JsonProperty("country")
    @Schema(description = "Название страны", example = "America")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название страны может содержать только буквы")
    private String country;

    @JsonProperty("company")
    @Schema(description = "Название компании в БД", example = "VK")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название компании может содержать только буквы")
    private String company;

    @JsonProperty("catalog")
    @Schema(description = "Название каталога в БД", example = "Rock")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название каталога может содержать только буквы")
    private String catalog;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления  картины в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("author")
    @Schema(description = "Какие авторы зарегистрированы на платформе",example = "Вивальди")
    private String author;

    @JsonProperty("musicName")
    private String musicName;
}
