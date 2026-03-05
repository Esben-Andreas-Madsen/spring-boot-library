package io.github.eama.spring_boot_library_backend.util;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.domain.Genre;

import java.util.Set;

public class TestDataFactory {

    public static Author createAuthor() {
        Author author = new Author();
        author.setName("Test Author");
        return author;
    }

    public static Book createBook(Author author) {

        Book book = new Book();
        book.setTitle("Test Book");
        book.setPublishedYear(2020);
        book.setIsbn("123456");
        book.setPages(200);
        book.setLanguage("English");
        book.setAuthors(Set.of(author));

        return book;
    }

    public static Genre createGenre() {
        Genre genre = new Genre();
        genre.setName("Test Genre");
        return genre;
    }

    public static CreateBookRequest createBookRequest(Integer authorId, Integer genreId) {

        CreateBookRequest request = new CreateBookRequest();
        request.setTitle("Test Book");
        request.setPublishedYear(2020);
        request.setIsbn("123456");
        request.setPages(200);
        request.setLanguage("English");
        request.setAuthorIds(Set.of(authorId));
        request.setGenreIds(Set.of(genreId));

        return request;
    }

}