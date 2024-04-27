package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.PlatformDto;
import ru.edu.penzgtu.lab.service.PlatformService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platforms")
@Tag(name = "Платформы", description = "Операции над платформами")
public class PlatformController {
    private final PlatformService platformService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех платформ", description = "Позволяет выгрузить все платформы из БД"
    )
    @GetMapping
    public ResponseWrapper<List<PlatformDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(platformService.findAllPlatforms());
    }

    @Operation(
            summary = "Получение платформы по ID", description = "Позволяет выгрузить одну платформу по ID из БД"
    )
    @GetMapping("/platform/{id}")
    public ResponseWrapper<PlatformDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(platformService.findPlatformById(id));
    }

    @Operation(
            summary = "Создать платформу", description = "Позволяет создать новую запись о платформе в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlatform(@RequestBody @Valid PlatformDto platform) {
        platformService.savePlatform(platform);
    }

    @Operation(
            summary = "Обновить данные о платформе", description = "Позволяет обновить данные о платформе"
    )
    @PutMapping("/platform/")
    public void updatePlatform(@RequestBody @Valid PlatformDto platform) {
        platformService.updatePlatform(platform);
    }

    @Operation(
            summary = "Удалить платформу по ID", description = "Позволяет удалить платформу по ID из БД"
    )
    @DeleteMapping("/platform/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlatform(@PathVariable @Min(0) Long id) {
        platformService.deletePlatformById(id);
    }
}
