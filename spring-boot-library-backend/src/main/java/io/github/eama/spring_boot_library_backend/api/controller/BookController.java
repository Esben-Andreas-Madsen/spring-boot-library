package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.repository.specification.BookFilter;
import io.github.eama.spring_boot_library_backend.service.BookService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ---------- GET BOOK BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        BookDto found = bookService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }

    // ---------- CREATE BOOK ----------
    @PostMapping
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookRequest request) {
        BookDto created = bookService.create(request);

        URI location = URI.create("/books/" + created.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(created);
    }

    // ---------- UPDATE BOOK ----------
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id,
                                              @Valid @RequestBody UpdateBookRequest request) {
        BookDto updated = bookService.update(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    // ---------- DELETE BOOK ----------
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // query books

    @GetMapping
    public ResponseEntity<Page<BookDto>> getBooks(@ParameterObject BookFilter filter,
                                                  @PageableDefault(size = 20, sort = "title")
                                                  @ParameterObject Pageable pageable) {
        Page<BookDto> foundBooks = bookService.getBooks(filter, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundBooks);
    }
}

