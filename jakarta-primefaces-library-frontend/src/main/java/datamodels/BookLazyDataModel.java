package datamodels;

import dto.BookDto;
import dto.PageDto;
import filter.BookFilter;
import service.BookService;
import util.FilterMapper;

import java.util.List;

public class BookLazyDataModel extends AbstractLazyDataModel<BookDto, BookFilter> {

    private final BookService bookService;

    public BookLazyDataModel(BookService bookService, BookFilter filter) {
        super(filter);
        this.bookService = bookService;
        this.filterMapper =new FilterMapper<BookFilter>()
                .map("title", String.class, BookFilter::setTitle)
                .map("isbn", String.class, BookFilter::setIsbn)
                .map("pagesFrom", Integer.class, BookFilter::setPagesFrom)
                .map("pagesTo", Integer.class, BookFilter::setPagesTo)
                .map("publishedYearFrom", Integer.class, BookFilter::setPublishedYearFrom)
                .map("publishedTo", Integer.class, BookFilter::setPublishedYearTo)
                .map("publishedYearFrom", Integer.class, BookFilter::setPublishedYearFrom)
                .map("publishedYearTo", Integer.class, BookFilter::setPublishedYearTo);
    }

    @Override
    protected PageDto<BookDto> fetch(int page, int size, BookFilter filter, List<String> sort) {
        return bookService.getBooks(page, size, filter, sort);
    }
}
