package service;

import client.BookApiClient;
import dto.BookDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Map;

@ApplicationScoped
public class BookService {

    @Inject
    @RestClient
    BookApiClient api;


    public PageDto<BookDto> getBooks(int page, int size, Map<String, String> filters) {

        return api.getBooks(
                page,
                size,
                filters.get("title"),
                filters.get("language"),
                filters.get("yearFrom"),
                filters.get("yearTo"),
                filters.get("isbn"),
                filters.get("pagesFrom"),
                filters.get("pagesTo")
        );
    }

    public PageDto<BookDto> getBooks(int page, int size) {
        return api.getBooks(page, size, null, null, null, null, null, null, null);
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