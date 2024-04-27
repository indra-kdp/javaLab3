package ru.edu.penzgtu.lab.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.lab.dto.AuthorDto;
import ru.edu.penzgtu.lab.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.AuthorRepository;
import ru.edu.penzgtu.lab.service.Mapper.AuthorMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    @Transactional
    public List<AuthorDto> findAllAuthors() {
        log.info("Найдены все существующие авторы в БД"); return authorMapper.toListDto(authorRepository.findAll());
    }

    public AuthorDto findAuthorById(Long id) {
        log.info("Найден автор по id" + id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Художник с id" + id + "не найден"));
        return authorMapper.toDto(author);
    }

    public void saveAuthor(AuthorDto authorDto) {
        log.info("Автор сохранен");
        Author author = authorMapper.toEntity(authorDto);
        author.setLocalDateTime(LocalDateTime.now());
        authorRepository.save(author);
    }

    public void updateAuthor(AuthorDto newAuthor) {
        log.info("Данные о авторе были обновлены");
        Author oldAuthor = authorRepository.findById(newAuthor.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Автор не найден"));

        oldAuthor.setName(newAuthor.getName());
        oldAuthor.setAge(newAuthor.getAge());
        oldAuthor.setPseudonym(newAuthor.getPseudonym());
        oldAuthor.setPublisher(newAuthor.getPublisher());

        authorRepository.save(oldAuthor);
    }

    public void deleteAuthorById(Long id) {
        log.info("Удален автор с id:" + id); authorRepository.deleteById(id);
    }
}
