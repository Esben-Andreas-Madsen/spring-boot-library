package client;

import dto.GenreDto;
import dto.PageDto;
import filter.GenreFilter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import security.AuthHeaderFactory;

import java.util.List;

@Path("/api/genres")
@RegisterClientHeaders(AuthHeaderFactory.class)
@RegisterRestClient(configKey = "genre-api", baseUri = "http://host.docker.internal:8080")
public interface GenreApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PageDto<GenreDto> getGenres(
            @QueryParam("page") int page,
            @QueryParam("size") int size,
            @BeanParam GenreFilter filter,
            @QueryParam("sort") List<String> sort
            );

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GenreDto getGenre(@PathParam("id") int id);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    GenreDto updateGenre(@PathParam("id") Integer id, GenreDto genre);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    GenreDto createGenre(GenreDto genre);

    @DELETE
    @Path("/{id}")
    void deleteGenre(
            @PathParam("id") int id
    );

}
