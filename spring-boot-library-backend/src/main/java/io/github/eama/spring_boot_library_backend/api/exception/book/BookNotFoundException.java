package io.github.eama.spring_boot_library_backend.api.exception.book;

import io.github.eama.spring_boot_library_backend.api.exception.NotFoundException;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(Integer id) {
        super("Book not found with id: " + id);
    }
}

