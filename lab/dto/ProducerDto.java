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
@Schema(description = "Информаци о продюсере")
public class ProducerDto {

    @JsonProperty("id")
    @Schema(description = "ID продюсера в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя продюсера может содержать только буквы")
    @Schema(description = "Имя продюсера", example = "СимплДимпл")
    private String name;

    @JsonProperty("sphere")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя сферы может содержать только буквы")
    @Schema(description = "Имя сферы", example = "ПопИТ")
    private String sphere;

    @JsonProperty("age")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Возраст продюсера может содержать только буквы")
    @Schema(description = "Возраст продюсера", example = "27")
    private String age;

    @JsonProperty("region")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название региона может содержать только буквы")
    @Schema(description = "Название региона", example = "USA")
    private String region;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления  картины в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("author")
    @Schema(description = "авторы которых продюсирует продюсер",example = "Трамп, Мияги...")
    private String author;
}
