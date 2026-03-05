package io.github.eama.spring_boot_library_backend.repository;

import io.github.eama.spring_boot_library_backend.util.AbstractRepositoryTest;
import io.github.eama.spring_boot_library_backend.util.AbstractServiceTest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.util.TestDataFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void shouldSaveBook() {

        Author author = authorRepository.save(TestDataFactory.createAuthor());

        Book book = TestDataFactory.createBook(author);

        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
    }
}