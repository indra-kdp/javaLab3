package ru.edu.penzgtu.lab.service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.AuthorDto;
import ru.edu.penzgtu.lab.entity.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorMapper {
    public List<AuthorDto> toListDto(List<Author> authors) {
        return authors.stream().map(this::toDto).toList();
    }

    public AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .pseudonym(author.getPseudonym())
                .publisher(author.getPublisher())
                .localDateTime(author.getLocalDateTime())
                .build();
    }

    public Author toEntity(AuthorDto authorDto) {
        Author author = new Author();

        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setPseudonym(authorDto.getPseudonym());
        author.setLocalDateTime(authorDto.getLocalDateTime());
        author.setPublisher(authorDto.getPublisher());

        return author;
    }
}
