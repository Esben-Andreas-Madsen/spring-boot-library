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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Inject
    BookService service;

    private LazyDataModel<BookDto> books;

    private Integer pagesFrom;
    private Integer pagesTo;

    private Integer yearFrom;
    private Integer yearTo;

    @PostConstruct
    public void init() {

        books = new LazyDataModel<>() {

            @Override
            public int count(Map<String, FilterMeta> filterBy) {
                return getRowCount();
            }

            @Override
            public List<BookDto> load(
                    int first,
                    int pageSize,
                    Map<String, SortMeta> sortBy,
                    Map<String, FilterMeta> filterBy) {

                int page = first / pageSize;

                Map<String, String> filters = filterBy.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getFilterValue() != null)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().getFilterValue().toString()
                        ));

                // should just apply a filter like the spring backend does.
                if (pagesFrom != null) {
                    filters.put("pagesFrom", pagesFrom.toString());
                }
                if (pagesTo != null) {
                    filters.put("pagesTo", pagesTo.toString());
                }
                if (yearFrom != null) {
                    filters.put("yearFrom", yearFrom.toString());
                }
                if (yearTo != null) {
                    filters.put("yearTo", yearTo.toString());
                }

                PageDto<BookDto> result = service.getBooks(page, pageSize, filters);

                setRowCount((int) result.getTotalElements());

                filterBy.forEach((k,v) ->
                        System.out.println(k + " -> " + v.getFilterValue())
                );

                return result.getContent();
            }
        };
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }

    public Integer getPagesFrom() {
        return pagesFrom;
    }

    public void setPagesFrom(Integer pagesFrom) {
        this.pagesFrom = pagesFrom;
    }

    public Integer getPagesTo() {
        return pagesTo;
    }

    public void setPagesTo(Integer pagesTo) {
        this.pagesTo = pagesTo;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }
}
