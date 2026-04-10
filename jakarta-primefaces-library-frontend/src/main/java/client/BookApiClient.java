package client;

import dto.BookDto;
import dto.PageDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import security.AuthHeaderFactory;


@Path("/api/books")
@RegisterClientHeaders(AuthHeaderFactory.class)
@RegisterRestClient(configKey = "book-api", baseUri = "http://host.docker.internal:8080")
public interface BookApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PageDto<BookDto> getBooks(
            @QueryParam("page") int page,
            @QueryParam("size") int size,
            @QueryParam("title") String title,
            @QueryParam("language") String language,
            // naming difference between here and backend api
            @QueryParam("publishedYearFrom") String yearFrom,
            @QueryParam("publishedYearTo") String yearTo,
            @QueryParam("isbn") String isbn,
            @QueryParam("pagesFrom") String pagesFrom,
            @QueryParam("pagesTo") String pagesTo
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