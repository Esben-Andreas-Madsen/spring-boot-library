package service;
import client.GenreApiClient;
import dto.GenreDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class GenreService {

    @Inject
    @RestClient
    GenreApiClient genreApiClient;

    public PageDto<GenreDto> getGenres(int page, int size) {
        return genreApiClient.getGenres(page, size);
    }

    public GenreDto getGenre(int id) {
        return genreApiClient.getGenre(id);
    }


    public List<GenreDto> getGenresByIds(List<Long> ids) {

        return ids.stream()
                .map(id -> genreApiClient.getGenre(id.intValue()))
                .toList();
    }
}
