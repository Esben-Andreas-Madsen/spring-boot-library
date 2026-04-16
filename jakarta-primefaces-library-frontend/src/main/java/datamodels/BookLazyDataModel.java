package datamodels;

import dto.BookDto;
import dto.PageDto;
import filter.BookFilter;
import service.BookService;

import java.util.List;

public class BookLazyDataModel extends AbstractLazyDataModel<BookDto, BookFilter> {

    private final BookService bookService;

    public BookLazyDataModel(BookService bookService, BookFilter filter) {
        super(filter);
        this.bookService = bookService;
    }

    @Override
    protected PageDto<BookDto> fetch(int page, int size, BookFilter filter, List<String> sort) {
        return bookService.getBooks(page, size, filter, sort);
    }

    @Override
    protected BookFilter newFilter() {
        return null;
    }
}
