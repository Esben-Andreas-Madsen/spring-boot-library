package io.github.eama.spring_boot_library_backend.api.exception.book;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("Book already exists with ISBN: " + isbn);
    }
}
