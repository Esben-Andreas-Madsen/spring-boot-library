package io.github.eama.spring_boot_library_backend.api.exception.author;

import io.github.eama.spring_boot_library_backend.api.exception.NotFoundException;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(Integer id) {
        super("Author not found with id: " + id);
    }
}