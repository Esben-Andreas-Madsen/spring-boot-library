package bean.author;

import bean.LazyCrudBean;
import datamodels.AuthorLazyDataModel;
import dto.AuthorDto;
import filter.AuthorFilter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AuthorService;

@Named
@ViewScoped
public class AuthorBean extends LazyCrudBean<AuthorDto, AuthorFilter> {

    @Inject
    private AuthorService authorService;

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
        //authorService.createAuthor(entity);
    }

    @Override
    protected void updateEntity(AuthorDto entity) {

    }

    @Override
    protected void deleteEntity(AuthorDto entity) {

    }

    @Override
    protected Object getId(AuthorDto entity) {
        return null;
    }

    public AuthorFilter getFilter() {
        return filter;
    }

}
