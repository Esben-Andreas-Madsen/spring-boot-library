package io.github.eama.spring_boot_library_backend.mapper;

import io.github.eama.spring_boot_library_backend.api.dto.response.AuthorDto;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
        // we handle authors manually
    Author toEntity(AuthorDto dto);


//    @Mapping(target = "bookIds", expression = "java(mapBooks(author.getBooks()))")
//    AuthorDto toDto(Author author);

    @Mapping(source = "books", target = "bookIds")
    AuthorDto toDto(Author author);


    default Set<Integer> mapBooks(Set<Book> books) {
        if (books == null) return Set.of();
        return books.stream()
                .map(Book::getId)
                .collect(Collectors.toSet());
    }
}
