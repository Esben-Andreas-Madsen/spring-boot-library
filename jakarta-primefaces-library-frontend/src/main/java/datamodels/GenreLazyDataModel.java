package datamodels;

import dto.BookDto;
import dto.GenreDto;
import dto.PageDto;
import filter.BookFilter;
import filter.GenreFilter;
import service.BookService;
import service.GenreService;

import java.util.List;

public class GenreLazyDataModel extends AbstractLazyDataModel<GenreDto, GenreFilter> {

    private final GenreService genreService;

    public GenreLazyDataModel(GenreService genreService, GenreFilter filter) {
        super(filter);
        this.genreService = genreService;
    }

    @Override
    protected PageDto<GenreDto> fetch(int page, int size, GenreFilter filter, List<String> sort) {
        return genreService.getGenres(page, size, filter, sort);
    }

    @Override
    protected GenreFilter newFilter() {
        return new GenreFilter();
    }
}
