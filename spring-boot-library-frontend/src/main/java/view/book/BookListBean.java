package view.book;

import dto.BookDto;
import dto.PageDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import service.BookService;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    transient BookService bookService;

    private transient LazyDataModel<BookDto> books;

    private PageDto<BookDto> lastPage;

    @PostConstruct
    public void init() {
        books = new LazyDataModel<>() {
            @Override
            public List<BookDto> load(int first, int pageSize,
                                      Map<String, SortMeta> sortBy,
                                      Map<String, FilterMeta> filterBy) {


                int page = first / pageSize;

                PageDto<BookDto> result = bookService.getBooks(page, pageSize);

                setRowCount((int) result.getTotalElements());

                return result.getContent();
            }

            @Override
            public int count(Map<String, FilterMeta> filterBy) {
                return (int) bookService.getBooks(0, 1).getTotalElements();
            }

            @Override
            public String getRowKey(BookDto book) {
                return String.valueOf(book.getId());
            }
        };
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }
}

