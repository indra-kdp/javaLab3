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
import ru.edu.penzgtu.lab.dto.ProducerDto;
import ru.edu.penzgtu.lab.service.ProducerService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
@Tag(name = "Продюсеры", description = "Операции над продюсерами")
public class ProducerController {
    private final BaseResponseService baseResponseService;
    private final ProducerService producerService;

    @Operation(
            summary = "Получение всех продюсеров", description = "Позволяет выгрузить всех продюсеров из БД"
    )
    @GetMapping
    public ResponseWrapper<List<ProducerDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(producerService.findAllProducers());
    }

    @Operation(
            summary = "Получение продюсера по ID", description = "Позволяет выгрузить одного продюсера по ID из БД"
    )
    @GetMapping("/producer/{id}")
    public ResponseWrapper<ProducerDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(producerService.findProducerById(id));
    }

    @Operation(
            summary = "Создать продюсера", description = "Позволяет создать новую запись о продюсере в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProducer(@RequestBody @Valid ProducerDto producer) {
        producerService.saveProducer(producer);
    }

    @Operation(
            summary = "Обновить данные о продюсере", description = "Позволяет обновить данные о продюсере"
    )
    @PutMapping("/producer/")
    public void updateProducer(@RequestBody @Valid ProducerDto producer) {
        producerService.updateProducer(producer);
    }

    @Operation(
            summary = "Удалить продюсера по ID", description = "Позволяет удалить продюсера по ID из БД"
    )
    @DeleteMapping("/producer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducer(@PathVariable @Min(0) Long id) {
        producerService.deleteProducerById(id);
    }
}

