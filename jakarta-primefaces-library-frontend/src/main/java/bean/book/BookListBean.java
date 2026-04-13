package bean.book;

import dto.BookDto;
import dto.PageDto;
import filter.BookFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import service.BookService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Inject
    BookService service;

    private LazyDataModel<BookDto> books;

    private BookFilter filter = new BookFilter();

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

                // apply PrimeFaces column filters
                applyFilters(filterBy, filter);

                // apply sorting
                List<String> sort = buildSort(sortBy);

                PageDto<BookDto> result = service.getBooks(page, pageSize, filter, sort);

                setRowCount((int) result.getTotalElements());

                return result.getContent();
            }
        };
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }

    public BookFilter getFilter() {
        return filter;
    }

    private List<String> buildSort(Map<String, SortMeta> sortBy) {

        if (sortBy == null || sortBy.isEmpty()) {
            return List.of("title,asc");
        }

        return sortBy.values().stream()
                .sorted(Comparator.comparingInt(SortMeta::getPriority))
                .filter(meta -> meta.getOrder() != SortOrder.UNSORTED)
                .map(meta -> meta.getField() + "," +
                        (meta.getOrder() == SortOrder.DESCENDING ? "desc" : "asc"))
                .toList();
    }

    private void applyFilters(Map<String, FilterMeta> filterBy, BookFilter filter) {

        filterBy.forEach((key, meta) -> {

            Object value = meta.getFilterValue();
            if (value == null) return;

            try {
                Field field = BookFilter.class.getDeclaredField(key);
                field.setAccessible(true);

                if (field.getType().equals(Integer.class)) {
                    field.set(filter, Integer.valueOf(value.toString()));
                } else {
                    field.set(filter, value.toString());
                }

            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        });
    }
}
