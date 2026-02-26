package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.mapper.BookMapper;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.domain.Book;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import io.github.eama.spring_boot_library_backend.repository.BookRepository;
import io.github.eama.spring_boot_library_backend.repository.specification.BookFilter;
import io.github.eama.spring_boot_library_backend.repository.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public BookDto findById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not Found"));


        return bookMapper.toDto(book);
    }

    // url querying

    public Page<BookDto> getBooks(BookFilter filter, Pageable pageable) {

        Specification<Book> spec = BookSpecification.build(filter);


        return bookRepository.findAll(spec, pageable).map(bookMapper::toDto);
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


