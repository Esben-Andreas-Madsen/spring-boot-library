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
    BookService bookService;

    @Inject
    GenreService genreService;

    @Inject
    AuthorService authorService;

    private LazyDataModel<BookDto> books;

    private BookFilter filter = new BookFilter();

    private BookDto selectedBook;

    private List<BookDto> selectedBooks = new ArrayList<>();

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

                PageDto<BookDto> result = bookService.getBooks(page, pageSize, filter, sort);

                setRowCount((int) result.getTotalElements());

                return result.getContent();
            }

            @Override
            public String getRowKey(BookDto book) {
                return book.getId().toString();
            }

            @Override
            public BookDto getRowData(String rowKey) {

                // look in current page first
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

    public void openNew() {
        selectedBook = new BookDto();
    }

    public void saveBook() {
        if (selectedBook.getId() == null) {
            bookService.createBook(selectedBook);
        } else {
            bookService.updateBook(selectedBook);
        }
    }

    public void deleteBook(BookDto book) {
        bookService.deleteBook(book.getId());
    }

    public void deleteSelectedBooks() {
        for (BookDto book : selectedBooks) {
            bookService.deleteBook(book.getId());
        }
    }

    public BookDto getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookDto selectedBook) {
        this.selectedBook = selectedBook;
    }

    public List<BookDto> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<BookDto> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }

    public List<GenreDto> getGenresByIds(Collection<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        return genreService.getGenresByIds(new ArrayList<>(ids));
    }

    public List<AuthorDto> getAuthorsByIds(Collection<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        return authorService.getAuthorsByIds(new ArrayList<>(ids));
    }

}
