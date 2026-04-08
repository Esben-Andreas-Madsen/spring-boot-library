package client;

import dto.BookDto;
import dto.PageDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//import security.AuthTokenFilter;
//
//@Path("/api/books")
//@RegisterProvider(AuthTokenFilter.class)
//@RegisterRestClient(configKey = "book-api", baseUri = "http://host.docker.internal:8080")
//public interface BookApiClient {
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    PageDto<BookDto> getBooks(
//            @QueryParam("page") int page,
//            @QueryParam("size") int size,
//            @QueryParam("title") String title,
//            @QueryParam("language") String language
//    );
//}