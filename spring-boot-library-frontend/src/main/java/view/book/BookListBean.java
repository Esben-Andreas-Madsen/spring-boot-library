package view.book;

import dto.BookDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.BookService;

import java.util.List;

@Named
@RequestScoped
public class BookListBean {

    @Inject
    BookService bookService;

    private List<BookDto> books;

    public List<BookDto> getBooks() {
        return books;
    }

    @PostConstruct
    public void init() {
        try {
            books = bookService.getBooks();
        } catch (Exception e) {
            e.printStackTrace();
            books = List.of();
        }
    }
}