package bean;

import dto.AuthorDto;
import dto.GenreDto;
import filter.AuthorFilter;
import filter.GenreFilter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.AuthorService;
import service.GenreService;

import java.util.List;

@Named
@RequestScoped
public class LookupBean {

    @Inject
    private AuthorService authorService;

    @Inject
    private GenreService genreService;

    public List<AuthorDto> searchAuthors(String query) {
        AuthorFilter filter = new AuthorFilter();
        filter.setName(query);

        return authorService
                .getAuthors(0, 10, filter, List.of("name,asc"))
                .getContent();
    }

    public List<GenreDto> searchGenres(String query) {
        GenreFilter filter = new GenreFilter();
        filter.setName(query);

        return genreService
                .getGenres(0, 10, filter, List.of("name,asc"))
                .getContent();
    }
}
