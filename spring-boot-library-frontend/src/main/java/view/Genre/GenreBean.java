package view.Genre;

import dto.AuthorDto;
import dto.GenreDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.GenreService;

@RequestScoped
@Named
public class GenreBean {

    @Inject
    GenreService genreService;

    private Integer genreId;
    private GenreDto genre;

    public void loadAuthor() {
        if (genreId != null) {
            genre = genreService.getGenre(genreId);
        }
    }

    public GenreDto getGenre() {
        return genre;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}