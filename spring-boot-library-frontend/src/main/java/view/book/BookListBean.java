package view.book;

import dto.AuthorDto;
import dto.BookDto;
import dto.GenreDto;
import dto.PageDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class BookListBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    transient BookService bookService;

    @Inject
    AuthorService authorService;

    @Inject
    GenreService genreService;


    private transient LazyDataModel<BookDto> books;

    private Map<Integer, AuthorDto> authorCache = new HashMap<>();
    private Map<Integer, GenreDto> genreCache = new HashMap<>();

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

    public AuthorDto getAuthorById(Integer authorId) {
        if (authorId == null) return null;

        // cache lookup
        if (!authorCache.containsKey(authorId)) {
            authorCache.put(authorId, authorService.getAuthor(authorId));
        }

        return authorCache.get(authorId);
    }

    public List<AuthorDto> getAuthorsByIds(Set<Integer> ids) {
        return ids.stream()
                .map(this::getAuthorById)
                .collect(Collectors.toList());
    }

    public GenreDto getGenreById(Integer id) {
        if (id == null) return null;

        if (!genreCache.containsKey(id)) {
            genreCache.put(id, genreService.getGenre(id));
        }

        return genreCache.get(id);
    }

    public List<GenreDto> getGenresByIds(Set<Integer> ids) {
        return ids.stream()
                .map(this::getGenreById)
                .collect(Collectors.toList());
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }
}

