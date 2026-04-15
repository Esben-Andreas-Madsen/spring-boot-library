package bean.book;

import dto.AuthorDto;
import dto.BookDto;
import dto.GenreDto;
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
import service.AuthorService;
import service.BookService;
import service.GenreService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;

    private LazyDataModel<BookDto> books;

    private BookFilter filter = new BookFilter();

    // lazy lookup cache
    private final Map<Long, AuthorDto> authorCache = new HashMap<>();
    private final Map<Long, GenreDto> genreCache = new HashMap<>();

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

                filter = new BookFilter();

                applyFilters(filterBy);

                List<String> sort = buildSort(sortBy);

                PageDto<BookDto> result =
                        bookService.getBooks(page, pageSize, filter, sort);

                setRowCount((int) result.getTotalElements());

                return result.getContent();
            }

            @Override
            public String getRowKey(BookDto book) {
                return book.getId().toString();
            }

            @Override
            public BookDto getRowData(String rowKey) {

                List<BookDto> current = (List<BookDto>) getWrappedData();

                if (current != null) {
                    for (BookDto book : current) {
                        if (book.getId().toString().equals(rowKey)) {
                            return book;
                        }
                    }
                }

                return null;
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

    private void applyFilters(Map<String, FilterMeta> filterBy) {

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

    public List<AuthorDto> getAuthorsByIds(Collection<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<Long> missing = ids.stream()
                .filter(id -> !authorCache.containsKey(id))
                .toList();

        if (!missing.isEmpty()) {
            List<AuthorDto> fetched = authorService.getAuthorsByIds(missing);
            fetched.forEach(a -> authorCache.put(Long.valueOf(a.getId()), a));
        }

        return ids.stream()
                .map(authorCache::get)
                .filter(Objects::nonNull)
                .toList();
    }

    public List<GenreDto> getGenresByIds(Collection<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<Long> missing = ids.stream()
                .filter(id -> !genreCache.containsKey(id))
                .toList();

        if (!missing.isEmpty()) {
            List<GenreDto> fetched = genreService.getGenresByIds(missing);
            fetched.forEach(g -> genreCache.put(Long.valueOf(g.getId()), g));
        }

        return ids.stream()
                .map(genreCache::get)
                .filter(Objects::nonNull)
                .toList();
    }
}