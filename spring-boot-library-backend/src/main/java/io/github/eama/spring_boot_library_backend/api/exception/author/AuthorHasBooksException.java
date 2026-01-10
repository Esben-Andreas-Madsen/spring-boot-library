package io.github.eama.spring_boot_library_backend.api.exception.author;

public class AuthorHasBooksException extends RuntimeException {
    public AuthorHasBooksException(String name) {
        super("Cannot delete author '" + name + "' because they have books");
    }
}
