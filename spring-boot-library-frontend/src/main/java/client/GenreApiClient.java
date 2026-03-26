package client;

import dto.AuthorDto;
import dto.GenreDto;
import dto.PageDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import security.AuthTokenFilter;

@Path("/api/genres")
@RegisterProvider(AuthTokenFilter.class)
@RegisterRestClient(configKey = "genre-api", baseUri = "http://host.docker.internal:8080")
public interface GenreApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PageDto<GenreDto> getGenres(@QueryParam("page") int page,
                                @QueryParam("size") int size);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GenreDto getGenre(@PathParam("id") int id);
}
