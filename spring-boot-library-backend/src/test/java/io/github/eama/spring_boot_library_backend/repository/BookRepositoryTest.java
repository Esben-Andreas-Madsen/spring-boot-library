package io.github.eama.spring_boot_library_backend.repository;

import io.github.eama.spring_boot_library_backend.PostgresTestContainerConfig;
import io.github.eama.spring_boot_library_backend.domain.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
class BookRepositoryTest extends PostgresTestContainerConfig {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveBook() {
        Book book = new Book();
        book.setTitle("Test");

        Book saved = bookRepository.save(book);

        assertNotNull(saved.getId());
    }
}