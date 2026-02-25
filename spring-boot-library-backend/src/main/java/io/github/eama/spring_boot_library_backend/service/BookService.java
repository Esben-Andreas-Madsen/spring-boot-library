package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.mapper.BookMapper;
import io.github.eama.spring_boot_library_backend.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import io.github.eama.spring_boot_library_backend.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    // ---------- READ ----------

    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public BookDto findById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not Found"));


        return bookMapper.toDto(book);
    }

    // ---------- CREATE ----------

    public BookDto create(CreateBookRequest request) {
        // Fetch authors from DB
        Set<Author> authors = new HashSet<>(
                authorRepository.findAllById(request.getAuthorIds())
        );

        // Create entity
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPublishedYear(request.getPublishedYear());
        book.setIsbn(request.getIsbn());
        book.setPages(request.getPages());
        book.setLanguage(request.getLanguage());
        book.setAuthors(authors);

        // Save
        Book saved = bookRepository.save(book);

        // Map to DTO
        return bookMapper.toDto(saved);
    }

    // ---------- UPDATE ----------

    public BookDto update(Integer id, UpdateBookRequest request) {
        Book book = bookMapper.toEntity(findById(id));

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

        return bookMapper.toDto(book);
    }

    // ---------- DELETE ----------

    public void delete(Integer id) {
        Book book = bookMapper.toEntity(findById(id));
        bookRepository.delete(book);
    }
}


