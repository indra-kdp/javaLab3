package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.MusicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.service.MusicService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
@Tag(name = "Музыка", description = "Операции над музыкой")
public class MusicController {
    public final MusicService musicService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всей музыки", description = "Позволяет выгрузить всю музыки из БД"
    )
    @GetMapping
    public ResponseWrapper<List<MusicDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(musicService.findAllMusics());
    }

    @Operation(
            summary = "Получение музыки по ID", description = "Позволяет выгрузить одну музыку по ID из БД"
    )
    @GetMapping("/music/{id}")
    public ResponseWrapper<MusicDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(musicService.findMusicById(id));
    }

    @Operation(
            summary = "Создать музыку", description = "Позволяет создать новую запись о музыке в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMusic(@RequestBody @Valid MusicDto music) {
        musicService.saveMusic(music);
    }

    @Operation(
            summary = "Обновить данные о музыке", description = "Позволяет обновить данные о музыке"
    )
    @PutMapping("/music/")
    public void updateMusic(@RequestBody @Valid MusicDto music) {
        musicService.updateMusic(music);
    }

    @Operation(
            summary = "Удалить музыку по ID", description = "Позволяет удалить музыку по ID из БД"
    )
    @DeleteMapping("/music/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusic(@PathVariable @Min(0) long id) {
        musicService.deleteMusicById(id);
    }
}
