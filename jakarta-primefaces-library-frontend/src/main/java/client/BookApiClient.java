package client;

import dto.BookDto;
import dto.PageDto;
import filter.BookFilter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import security.AuthHeaderFactory;

import java.util.List;


@Path("/api/books")
@RegisterClientHeaders(AuthHeaderFactory.class)
@RegisterRestClient(configKey = "book-api", baseUri = "http://host.docker.internal:8080")
public interface BookApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PageDto<BookDto> getBooks(
            @QueryParam("page") int page,
            @QueryParam("size") int size,
            @BeanParam BookFilter filter,
            @QueryParam("sort") List<String> sort
            );

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    BookDto getBook(
            @PathParam("id") int id
    );

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    BookDto editBook(BookDto book);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    BookDto createBook(BookDto book);

    @DELETE
    @Path("/{id}")
    void deleteBook(
            @PathParam("id") int id
    );
}