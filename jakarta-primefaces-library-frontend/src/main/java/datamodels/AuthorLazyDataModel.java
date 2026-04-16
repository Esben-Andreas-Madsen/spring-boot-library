package datamodels;

import dto.AuthorDto;
import dto.PageDto;
import filter.AuthorFilter;
import service.AuthorService;

import java.util.List;

public class AuthorLazyDataModel extends AbstractLazyDataModel<AuthorDto, AuthorFilter> {

    private final AuthorService authorService;

    public AuthorLazyDataModel(AuthorService authorService, AuthorFilter filter) {
        super(filter);
        this.authorService = authorService;
    }

    @Override
    protected PageDto<AuthorDto> fetch(int page, int size, AuthorFilter filter, List<String> sort) {
        return authorService.getAuthors(page, size, filter, sort);
    }

    @Override
    protected AuthorFilter newFilter() {
        return new AuthorFilter();
    }
}
