package io.github.eama.spring_boot_library_backend.mapper;

import io.github.eama.spring_boot_library_backend.api.dto.response.GenreDto;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "books", ignore = true)
    Genre toEntity(GenreDto dto);

    @Mapping(source = "books", target = "bookIds")
    GenreDto toDto(Genre genre);

    default Set<Integer> mapBooks(Set<Book> books) {
        if (books == null) return Set.of();
        return books.stream()
                .map(Book::getId)
                .collect(Collectors.toSet());
    }
}
