package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.api.exception.book.BookNotFoundException;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.domain.Genre;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import io.github.eama.spring_boot_library_backend.repository.BookRepository;
import io.github.eama.spring_boot_library_backend.repository.GenreRepository;
import io.github.eama.spring_boot_library_backend.util.AbstractServiceTest;
import io.github.eama.spring_boot_library_backend.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
class BookServiceTest extends AbstractServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void shouldCreateBook() {

        Author author = authorRepository.save(TestDataFactory.createAuthor());
        Genre genre = genreRepository.save(TestDataFactory.createGenre());

        CreateBookRequest request =
                TestDataFactory.createBookRequest(author.getId(), genre.getId());

        BookDto result = bookService.create(request);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Book");
        assertThat(result.getAuthorIds()).isNotNull();
    }

    @Test
    void shouldFindBookById() {

        Author author = authorRepository.save(TestDataFactory.createAuthor());

        Book book = TestDataFactory.createBook(author);
        book = bookRepository.save(book);

        BookDto result = bookService.findById(book.getId());

        assertThat(result.getTitle()).isEqualTo("Test Book");
    }

    @Test
    void shouldUpdateBook() {

        Author author = authorRepository.save(TestDataFactory.createAuthor());

        Book book = TestDataFactory.createBook(author);
        book = bookRepository.save(book);

        UpdateBookRequest request = new UpdateBookRequest();
        request.setTitle("Updated Title");
        request.setPages(400);

        BookDto updated = bookService.update(book.getId(), request);

        assertThat(updated.getTitle()).isEqualTo("Updated Title");
        assertThat(updated.getPages()).isEqualTo(400);
    }

    @Test
    void shouldDeleteBook() {

        Author author = authorRepository.save(TestDataFactory.createAuthor());

        Book book = TestDataFactory.createBook(author);
        book = bookRepository.save(book);

        bookService.delete(book.getId());

        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

    @Test
    void shouldThrowWhenBookNotFound() {

        assertThatThrownBy(() -> bookService.findById(999))
                .isInstanceOf(BookNotFoundException.class);
    }
}