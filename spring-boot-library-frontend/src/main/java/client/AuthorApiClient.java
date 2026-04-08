package client;

import dto.AuthorDto;
import dto.BookDto;
import dto.PageDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//@Path("/api/authors")
//@RegisterProvider(AuthTokenFilter.class)
//@RegisterRestClient(configKey = "author-api", baseUri = "http://host.docker.internal:8080")
//public interface AuthorApiClient {
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    PageDto<AuthorDto> getAuthors(@QueryParam("page") int page,
//                              @QueryParam("size") int size);
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    AuthorDto getAuthor(@PathParam("id") int id);
//}
