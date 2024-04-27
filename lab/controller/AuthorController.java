package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.service.AuthorService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Tag(name = "Авторы", description = "Операции над авторами")
public class AuthorController {
    private final AuthorService authorService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех авторов", description = "Позволяет выгрузить всех авторов из БД"
    )
    @GetMapping
    public ResponseWrapper<List<AuthorDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(authorService.findAllAuthors());
    }

    @Operation(
            summary = "Получение атвора по ID", description = "Позволяет выгрузить одного автора по ID из БД"
    )
    @GetMapping("/author/{id}")
    public ResponseWrapper<AuthorDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(authorService.findAuthorById(id));
    }

    @Operation(
            summary = "Создать автора", description = "Позволяет создать новую запись об авторе в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody @Valid AuthorDto author) {
        authorService.saveAuthor(author);
    }

    @Operation(
            summary = "Обновить данные об авторе", description = "Позволяет обновить данные об авторе"
    )
    @PutMapping("/author/")
    public void updateAuthor(@RequestBody @Valid AuthorDto author) {
        authorService.updateAuthor(author);
    }

    @Operation(
            summary = "Удалить автора по ID", description = "Позволяет удалить автора по ID из БД"
    )
    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable @Min(0) Long id) {
        authorService.deleteAuthorById(id);
    }
}
