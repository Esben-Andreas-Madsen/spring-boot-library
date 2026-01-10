package io.github.eama.spring_boot_library_backend.api.mapper;

import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.domain.Book;

import java.util.stream.Collectors;

public class BookMapper {

    // Full mapping: Book → BookDto
    public static BookDto toDto(Book book) {
        if (book == null) return null;

        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublishedYear(book.getPublishedYear());
        dto.setIsbn(book.getIsbn());
        dto.setPages(book.getPages());
        dto.setLanguage(book.getLanguage());

        // Map authors shallowly to avoid infinite recursion
        dto.setAuthors(
                book.getAuthors().stream()
                        .map(AuthorMapper::toDtoShallow)
                        .collect(Collectors.toSet())
        );

        return dto;
    }

    // Optional: shallow mapping without authors (if needed)
    public static BookDto toDtoShallow(Book book) {
        if (book == null) return null;

        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublishedYear(book.getPublishedYear());
        dto.setIsbn(book.getIsbn());
        dto.setPages(book.getPages());
        dto.setLanguage(book.getLanguage());
        return dto;
    }
}

