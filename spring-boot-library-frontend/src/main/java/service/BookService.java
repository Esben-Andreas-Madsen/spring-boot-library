package service;

//import client.BookApiClient;
import dto.BookDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

//@ApplicationScoped
//public class BookService {
//
//    @Inject
//    @RestClient
//    BookApiClient bookApiClient;
//
//    public PageDto<BookDto> getBooks(int page, int size, Map<String, Object> filters) {
//
//        return bookApiClient.getBooks(
//                page,
//                size,
//                (String) filters.get("title"),
//                (String) filters.get("language")
//        );
//    }
//
//    public PageDto<BookDto> getBooks(int page, int size) {
//        return bookApiClient.getBooks(page, size, null, null);
//    }
//}