package view.book;

import dto.BookDto;
import dto.PageDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.FilterMeta;
import service.BookService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Inject
    BookService service;

    private LazyDataModel<BookDto> books;

    private String titleFilter;
    private String languageFilter;

    @PostConstruct
    public void init() {

        books = new LazyDataModel<>() {

            @Override
            public List<BookDto> load(
                    int first,
                    int pageSize,
                    Map<String, SortMeta> sortBy,
                    Map<String, FilterMeta> filterBy) {

                int page = first / pageSize;

                titleFilter = getFilterValue(filterBy, "title");
                languageFilter = getFilterValue(filterBy, "language");

                PageDto<BookDto> result = service.getBooks(
                        page,
                        pageSize
                );

                return result.getContent();
            }

            @Override
            public int count(Map<String, FilterMeta> filterBy) {

                titleFilter = getFilterValue(filterBy, "title");
                languageFilter = getFilterValue(filterBy, "language");

                var result = service.getBooks(
                        0,
                        1
                );

                return (int) result.getTotalElements();
            }
        };
    }

    private String getFilterValue(Map<String, FilterMeta> filterBy, String key) {

        FilterMeta meta = filterBy.get(key);

        if (meta == null || meta.getFilterValue() == null)
            return null;

        return meta.getFilterValue().toString();
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }
}
