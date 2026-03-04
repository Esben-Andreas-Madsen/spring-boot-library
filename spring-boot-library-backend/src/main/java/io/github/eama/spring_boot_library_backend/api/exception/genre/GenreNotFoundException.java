package io.github.eama.spring_boot_library_backend.api.exception.genre;

import io.github.eama.spring_boot_library_backend.api.exception.NotFoundException;

public class GenreNotFoundException extends NotFoundException {
    public GenreNotFoundException(Integer id) {
        super("Genre not found with id: " + id);
    }
}
