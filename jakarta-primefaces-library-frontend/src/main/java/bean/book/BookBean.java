package bean.book;

import bean.LazyCrudBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import util.EntityCache;
import datamodels.BookLazyDataModel;
import dto.AuthorDto;
import dto.BookDto;
import dto.GenreDto;
import filter.BookFilter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class BookBean extends LazyCrudBean<BookDto, BookFilter> {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;


    private final EntityCache<Long, AuthorDto> authorCache = new EntityCache<>();
    private final EntityCache<Long, GenreDto> genreCache = new EntityCache<>();

    private List<AuthorDto> selectedAuthors = new ArrayList<>();
    private List<GenreDto> selectedGenres = new ArrayList<>();


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

    @Override
    public void save() {

        Set<Long> authorIds = selectedAuthors.stream()
                .map(a -> Long.valueOf(a.getId()))
                .collect(Collectors.toSet());

        Set<Long> genreIds = selectedGenres.stream()
                .map(g -> Long.valueOf(g.getId()))
                .collect(Collectors.toSet());

        if (selected != null && selected.getId() != null) {
            // edit
            selected.setAuthorIds(authorIds);
            selected.setGenreIds(genreIds);

        } else {
            // create
            create.setAuthorIds(authorIds);
            create.setGenreIds(genreIds);
        }

        super.save();

        selectedAuthors.clear();
        selectedGenres.clear();
    }


    public List<AuthorDto> getSelectedAuthors() {
        return selectedAuthors;
    }

    public List<GenreDto> getSelectedGenres() {
        return selectedGenres;
    }

    public void setSelectedAuthors(List<AuthorDto> selectedAuthors) {
        this.selectedAuthors = selectedAuthors;
    }

    public void setSelectedGenres(List<GenreDto> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    public void addAuthor(AuthorDto author) {
        if (!selectedAuthors.contains(author)) {
            selectedAuthors.add(author);
        }
    }

    public void removeAuthor(AuthorDto author) {
        selectedAuthors.remove(author);
    }

    public void addGenre(GenreDto genre) {
        if (!selectedGenres.contains(genre)) {
            selectedGenres.add(genre);
        }
    }

    public void removeGenre(GenreDto genre) {
        selectedGenres.remove(genre);
    }

    @PostConstruct
    public void initEdit() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null) {

            Long id = Long.valueOf(idParam);

            selected = bookService.getBook(Math.toIntExact(id));

            selectedAuthors = new ArrayList<>(
                    getAuthorsByIds(selected.getAuthorIds())
            );

            selectedGenres = new ArrayList<>(
                    getGenresByIds(selected.getGenreIds())
            );
        }
    }

    public void delete() {
        deleteEntity(selected);
        selected = null;
    }

    public void loadForEdit(Long id) {
        selected = bookService.getBook(id.intValue());

        selectedAuthors = selected.getAuthorIds() == null
                ? new ArrayList<>()
                : new ArrayList<>(getAuthorsByIds(selected.getAuthorIds()));

        selectedGenres = selected.getGenreIds() == null
                ? new ArrayList<>()
                : new ArrayList<>(getGenresByIds(selected.getGenreIds()));
    }
}