package bean.book;

import bean.LazyCrudBean;
import cache.EntityCache;
import datamodels.BookLazyDataModel;
import dto.AuthorDto;
import dto.BookDto;
import dto.GenreDto;
import filter.BookFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Named
@ViewScoped
public class BookBean extends LazyCrudBean<BookDto, BookFilter> {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;

    private LazyDataModel<BookDto> books;

    private BookFilter filter = new BookFilter();

    private final EntityCache<Long, AuthorDto> authorCache = new EntityCache<>();
    private final EntityCache<Long, GenreDto> genreCache = new EntityCache<>();

    @PostConstruct
    public void init() {
        books = new BookLazyDataModel(bookService, filter);
    }

    public LazyDataModel<BookDto> getBooks() {
        return books;
    }

    public BookFilter getFilter() {
        return filter;
    }

    public List<AuthorDto> getAuthorsByIds(Collection<Long> ids) {
        return authorCache.resolve(ids,
                authorService::getAuthorsByIds,
                a -> Long.valueOf(a.getId()));
    }

    public List<GenreDto> getGenresByIds(Collection<Long> ids) {
        return genreCache.resolve(ids,
                genreService::getGenresByIds,
                g -> Long.valueOf(g.getId()));
    }

    @Override
    protected LazyDataModel<BookDto> newLazyModel(BookFilter filter) {
        return new BookLazyDataModel(bookService, filter);
    }

    @Override
    protected BookFilter newFilter() {
        return new BookFilter();
    }

    @Override
    protected BookDto newEntity() {
        return new BookDto();
    }

    @Override
    protected void createEntity(BookDto entity) {
        bookService.createBook(entity);
    }

    @Override
    protected void updateEntity(BookDto entity) {
        bookService.updateBook(entity);
    }

    @Override
    protected void deleteEntity(BookDto entity) {
        bookService.deleteBook(entity.getId());
    }

    @Override
    protected Object getId(BookDto entity) {
        return entity.getId();
    }
}