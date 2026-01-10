package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.api.mapper.BookMapper;
import io.github.eama.spring_boot_library_backend.service.BookService;
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
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    // ---------- GET BOOK BY ID ----------
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Integer id) {
        return BookMapper.toDto(bookService.findById(id));
    }

    // ---------- CREATE BOOK ----------
    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookRequest request) {
        return BookMapper.toDto(bookService.create(request));
    }

    // ---------- UPDATE BOOK ----------
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Integer id,
                              @Valid @RequestBody UpdateBookRequest request) {
        return BookMapper.toDto(bookService.update(id, request));
    }

    // ---------- DELETE BOOK ----------
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
    }
}

