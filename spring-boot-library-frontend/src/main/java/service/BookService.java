package service;

import client.BookApiClient;
import dto.BookDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BookService {

    @Inject
    @RestClient
    BookApiClient bookApiClient;

    public List<BookDto> getBooks() {
        PageDto<BookDto> page = bookApiClient.getBooks();
        return page.getContent();
    }
}