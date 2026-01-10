package io.github.eama.spring_boot_library_backend.api.mapper;

import io.github.eama.spring_boot_library_backend.api.dto.response.AuthorDto;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;

import java.util.stream.Collectors;

public class AuthorMapper {

    // Full mapping: Author → AuthorDto
    public static AuthorDto toDto(Author author) {
        if (author == null) return null;

        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBirthYear(author.getBirthYear());

        // Avoid deep nesting: just return book IDs
        dto.setBookIds(
                author.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet())
        );

        return dto;
    }

    // Shallow mapping: Author → AuthorDto (without books)
    public static AuthorDto toDtoShallow(Author author) {
        if (author == null) return null;

        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBirthYear(author.getBirthYear());
        return dto;
    }
}

