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
    BookApiClient api;

    public PageDto<BookDto> getBooks(int page, int size, BookFilter filter, List<String> sort) {
        return api.getBooks(page, size, filter, sort);
    }

    public PageDto<BookDto> getBooks(int page, int size) {
        return api.getBooks(page, size, new BookFilter(), null);
    }

    public BookDto getBook(int id) {
        return api.getBook(id);
    }

    public BookDto createBook(BookDto book) {
        return api.createBook(book);
    }

    public BookDto editBook(BookDto book) {
        return api.editBook(book);
    }

    public void deleteBook(int id) {
        api.deleteBook(id);
    }
}