package io.github.eama.spring_boot_library_backend.mapper;

import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.domain.Book;

import java.util.stream.Collectors;

import io.github.eama.spring_boot_library_backend.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book toEntity(BookDto dto);

    @Mapping(source = "genres", target = "genreIds")
    @Mapping(source = "authors", target = "authorIds")
    BookDto toDto(Book book);


    default Set<Integer> mapAuthors(Set<Author> authors) {
        if (authors == null) return Set.of();
        return authors.stream()
                .map(Author::getId)
                .collect(Collectors.toSet());
    }

    default Set<Integer> mapGenres(Set<Genre> genres) {
        if (genres == null) return Set.of();
        return genres.stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());
    }
}
