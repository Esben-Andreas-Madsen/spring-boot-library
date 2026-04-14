package service;

import client.AuthorApiClient;
import dto.AuthorDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class AuthorService {

    @Inject
    @RestClient
    AuthorApiClient authorApiClient;

    public PageDto<AuthorDto> getAuthors(int page, int size) {
        return authorApiClient.getAuthors(page, size);
    }

    public AuthorDto getAuthor(int id) {
        return authorApiClient.getAuthor(id);
    }

    public List<AuthorDto> getAuthorsByIds(List<Long> ids) {

        return ids.stream()
                .map(id -> authorApiClient.getAuthor(id.intValue()))
                .toList();
    }
}
