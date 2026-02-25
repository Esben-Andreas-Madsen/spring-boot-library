package io.github.eama.spring_boot_library_backend.api.mapper;

import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.domain.Book;

import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authors", ignore = true)
        // we handle authors manually
    Book toEntity(BookDto dto);

//    @Mapping(target = "authors", expression = "java(mapAuthors(book.getAuthors()))")
//    BookDto toDto(Book book);

    @Mapping(source = "authors", target = "authorIds")
    BookDto toDto(Book book);

    default Set<Integer> mapAuthors(Set<Author> authors) {
        if (authors == null) return Set.of();
        return authors.stream()
                .map(Author::getId)
                .collect(Collectors.toSet());
    }
}
