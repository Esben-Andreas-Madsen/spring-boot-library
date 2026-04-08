package service;

//import client.AuthorApiClient;
//import client.GenreApiClient;
import dto.AuthorDto;
import dto.GenreDto;
import dto.PageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

//@ApplicationScoped
//public class GenreService {
//
//    @Inject
//    @RestClient
//    GenreApiClient genreApiClient;
//
//    public PageDto<GenreDto> getGenres(int page, int size) {
//        return genreApiClient.getGenres(page, size);
//    }
//
//    public GenreDto getGenre(int id) {
//        return genreApiClient.getGenre(id);
//    }
//
//
//}
