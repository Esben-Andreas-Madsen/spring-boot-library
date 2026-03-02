package io.github.eama.spring_boot_library_backend.api.exception.genre;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Integer id) {
        super("Genre not found with id: " + id);
    }
}
