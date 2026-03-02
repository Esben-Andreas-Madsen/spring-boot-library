package io.github.eama.spring_boot_library_backend.api.exception.genre;

public class DuplicateGenreException extends RuntimeException {
    public DuplicateGenreException(String name) {
        super("Genre already exists with name " + name);
    }

}
