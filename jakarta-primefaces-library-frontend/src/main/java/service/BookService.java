package service;

import client.BookApiClient;
import dto.BookDto;
import dto.PageDto;
import filter.BookFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    @RestClient
    BookApiClient bookApiClient;

    public PageDto<BookDto> getBooks(int page, int size, BookFilter filter, List<String> sort) {
        return bookApiClient.getBooks(page, size, filter, sort);
    }

    public PageDto<BookDto> getBooks(int page, int size) {
        return bookApiClient.getBooks(page, size, new BookFilter(), null);
    }

    public BookDto getBook(int id) {
        return bookApiClient.getBook(id);
    }

    public BookDto createBook(BookDto book) {
        System.out.println(book.toString());
        return bookApiClient.createBook(book);
    }

    public BookDto updateBook(BookDto book) {
        return bookApiClient.updateBook(book.getId(), book);
    }

    public void deleteBook(int id) {
        bookApiClient.deleteBook(id);
    }
}