package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import io.github.eama.spring_boot_library_backend.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // ---------- READ ----------

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ---------- CREATE ----------

    public Book create(CreateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPublishedYear(request.getPublishedYear());
        book.setIsbn(request.getIsbn());
        book.setPages(request.getPages());
        book.setLanguage(request.getLanguage());

        if (request.getAuthorIds() != null && !request.getAuthorIds().isEmpty()) {
            Set<Author> authors = new HashSet<>(
                    authorRepository.findAllById(request.getAuthorIds())
            );
            book.setAuthors(authors);
        }

        return bookRepository.save(book);
    }

    // ---------- UPDATE ----------

    public Book update(Integer id, UpdateBookRequest request) {
        Book book = findById(id);

        book.setTitle(request.getTitle());
        book.setPublishedYear(request.getPublishedYear());
        book.setIsbn(request.getIsbn());
        book.setPages(request.getPages());
        book.setLanguage(request.getLanguage());

        if (request.getAuthorIds() != null) {
            Set<Author> authors = new HashSet<>(
                    authorRepository.findAllById(request.getAuthorIds())
            );
            book.setAuthors(authors);
        }

        return book;
    }

    // ---------- DELETE ----------

    public void delete(Integer id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }
}


