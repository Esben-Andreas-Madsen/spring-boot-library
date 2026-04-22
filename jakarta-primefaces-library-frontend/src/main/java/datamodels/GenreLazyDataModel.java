package datamodels;

import dto.GenreDto;
import dto.PageDto;
import filter.GenreFilter;
import service.GenreService;
import util.FilterMapper;

import java.util.List;

public class GenreLazyDataModel extends AbstractLazyDataModel<GenreDto, GenreFilter> {

    private final GenreService genreService;

    public GenreLazyDataModel(GenreService genreService, GenreFilter filter) {
        super(filter);
        this.genreService = genreService;
        this.filterMapper = new FilterMapper<GenreFilter>()
                .map("name", String.class, GenreFilter::setName)
                .map("bookId", Integer.class, GenreFilter::setBookId);
    }

    @Override
    protected PageDto<GenreDto> fetch(int page, int size, GenreFilter filter, List<String> sort) {
        return genreService.getGenres(page, size, filter, sort);
    }
}
