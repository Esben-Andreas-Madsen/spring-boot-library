package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.api.mapper.BookMapper;
import io.github.eama.spring_boot_library_backend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ---------- GET ALL BOOKS ----------
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = bookService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allBooks);
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
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookRequest request) {
        BookDto created = bookService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    // ---------- UPDATE BOOK ----------
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @Valid @RequestBody UpdateBookRequest request) {
        BookDto updated = bookService.update(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    // ---------- DELETE BOOK ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

