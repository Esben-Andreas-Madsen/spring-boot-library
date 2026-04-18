package datamodels;

import dto.AuthorDto;
import dto.PageDto;
import filter.AuthorFilter;
import service.AuthorService;
import util.FilterMapper;

import java.util.List;

public class AuthorLazyDataModel extends AbstractLazyDataModel<AuthorDto, AuthorFilter> {

    private final AuthorService authorService;

    public AuthorLazyDataModel(AuthorService authorService, AuthorFilter filter) {
        super(filter);
        this.authorService = authorService;
        this.filterMapper = new FilterMapper<AuthorFilter>()
                .map("name", String.class, AuthorFilter::setName)
                .map("birthYearFrom", Integer.class, AuthorFilter::setBirthYearFrom)
                .map("birthYearTo", Integer.class, AuthorFilter::setBirthYearTo)
                .map("bookId", Integer.class, AuthorFilter::setBookId);
    }

    @Override
    protected PageDto<AuthorDto> fetch(int page, int size, AuthorFilter filter, List<String> sort) {
        return authorService.getAuthors(page, size, filter, sort);
    }
}
