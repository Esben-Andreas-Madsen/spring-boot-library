package bean.author;

import datamodels.AuthorLazyDataModel;
import dto.AuthorDto;
import filter.AuthorFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AuthorService;

import java.io.Serializable;

@Named
@ViewScoped
public class AuthorBean implements Serializable {

    @Inject
    private AuthorService authorService;

    private LazyDataModel<AuthorDto> authors;

    private AuthorFilter filter = new AuthorFilter();

    @PostConstruct
    public void init() {
        authors = new AuthorLazyDataModel(authorService, filter);
    }

    public LazyDataModel<AuthorDto> getAuthors() {
        return authors;
    }
}
