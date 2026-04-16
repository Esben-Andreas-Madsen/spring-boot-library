package client;

import dto.AuthorDto;
import dto.PageDto;
import filter.AuthorFilter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import security.AuthHeaderFactory;

import java.util.List;

@Path("/api/authors")
@RegisterClientHeaders(AuthHeaderFactory.class)
@RegisterRestClient(configKey = "author-api", baseUri = "http://host.docker.internal:8080")
public interface AuthorApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PageDto<AuthorDto> getAuthors(
            @QueryParam("page") int page,
            @QueryParam("size") int size,
            @BeanParam AuthorFilter filter,
            @QueryParam("sort") List<String> sort
            );

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    AuthorDto getAuthor(@PathParam("id") int id);
}
