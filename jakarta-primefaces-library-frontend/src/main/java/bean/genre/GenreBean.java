package bean.genre;

import bean.LazyCrudBean;
import datamodels.GenreLazyDataModel;
import dto.GenreDto;
import filter.GenreFilter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.GenreService;

@Named
@ViewScoped
public class GenreBean extends LazyCrudBean<GenreDto, GenreFilter> {

    @Inject
    private GenreService genreService;

    @Override
    protected LazyDataModel<GenreDto> newLazyModel(GenreFilter filter) {
        return new GenreLazyDataModel(genreService, filter);
    }

    @Override
    protected GenreFilter newFilter() {
        return new GenreFilter();
    }

    @Override
    protected GenreDto newEntity() {
        return new GenreDto();
    }

    @Override
    protected void createEntity(GenreDto entity) {

    }

    @Override
    protected void updateEntity(GenreDto entity) {

    }

    @Override
    protected void deleteEntity(GenreDto entity) {

    }

    @Override
    protected Object getId(GenreDto entity) {
        return null;
    }

    public GenreFilter getFilter() {
        return filter;
    }
}