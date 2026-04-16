package bean.genre;

import datamodels.GenreLazyDataModel;
import dto.GenreDto;
import filter.GenreFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.GenreService;

import java.io.Serializable;

@Named
@ViewScoped
public class GenreBean implements Serializable {

    @Inject
    private GenreService genreService;

    private LazyDataModel<GenreDto> genres;

    private GenreFilter filter = new GenreFilter();

    @PostConstruct
    public void init() {
        genres = new GenreLazyDataModel(genreService, filter);
    }

    public LazyDataModel<GenreDto> getGenres() {
        return genres;
    }
}