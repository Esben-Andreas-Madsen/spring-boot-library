package bean.author;

import bean.LazyCrudBean;
import datamodels.AuthorLazyDataModel;
import dto.AuthorDto;
import dto.BookDto;
import filter.AuthorFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AuthorService;
import service.BookService;
import util.EntityCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AuthorBean extends LazyCrudBean<AuthorDto, AuthorFilter> {

    @Inject
    private AuthorService authorService;

    @Inject
    private BookService bookService;

    private List<BookDto> selectedBooks = new ArrayList<>();

    private final EntityCache<Long, BookDto> bookCache = new EntityCache<>();

    @Override
    protected LazyDataModel<AuthorDto> newLazyModel(AuthorFilter filter) {
        return new AuthorLazyDataModel(authorService, filter);
    }

    @Override
    protected AuthorFilter newFilter() {
        return new AuthorFilter();
    }

    @Override
    protected AuthorDto newEntity() {
        return new AuthorDto();
    }

    @Override
    protected void createEntity(AuthorDto entity) {
        authorService.createAuthor(entity);
    }

    @Override
    protected void updateEntity(AuthorDto entity) {
        authorService.updateAuthor(entity);
    }

    @Override
    protected void deleteEntity(AuthorDto entity) {
        authorService.deleteAuthor(entity.getId());
    }

    @Override
    protected Object getId(AuthorDto entity) {
        return entity.getId();
    }

    @Override
    public AuthorFilter getFilter() {
        return filter;
    }

    @Override
    public AuthorDto getSelected() {
        return selected;
    }

    @Override
    public void setSelected(AuthorDto selected) {
        this.selected = selected;
    }

    @Override
    public void save() {

        Set<Long> bookIds = selectedBooks.stream()
                .map(b -> Long.valueOf(b.getId()))
                .collect(Collectors.toSet());

        if (selected != null && selected.getId() != null) {
            selected.setBookIds(bookIds);
        } else {
            create.setBookIds(bookIds);
        }

        super.save();

        selectedBooks.clear();
    }

    public List<BookDto> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<BookDto> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }

    public void addBook(BookDto book) {
        if (!selectedBooks.contains(book)) {
            selectedBooks.add(book);
        }
    }

    public void removeBook(BookDto book) {
        selectedBooks.remove(book);
    }

//    @PostConstruct
//    public void initEdit() {
//
//        String idParam = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("id");
//
//        if (idParam != null) {
//
//            Long id = Long.valueOf(idParam);
//
//            selected = authorService.getAuthor(Math.toIntExact(id));
//
//            selectedBooks = new ArrayList<>(
//                    getBooksByIds(selected.getBookIds())
//            );
//        }
//    }

    public void loadFromId() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam == null) return;

        Long id = Long.valueOf(idParam);

        selected = authorService.getAuthor(Math.toIntExact(id));

        selectedBooks = selected.getBookIds() == null
                ? new ArrayList<>()
                : new ArrayList<>(getBooksByIds(selected.getBookIds()));
    }

    public List<BookDto> getBooksByIds(Collection<Long> ids) {
        return bookCache.resolve(ids,
                bookService::getBooksByIds,
                g -> Long.valueOf(g.getId()));
    }
}
