package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.book.CreateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.book.UpdateBookRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.BookDto;
import io.github.eama.spring_boot_library_backend.service.BookService;
import io.github.eama.spring_boot_library_backend.util.AbstractControllerTest;
import io.github.eama.spring_boot_library_backend.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = BookController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                OAuth2ResourceServerAutoConfiguration.class
        }
)
class BookControllerTest extends AbstractControllerTest {

    @MockitoBean
    private BookService bookService;

    @Test
    void shouldReturnBookById() throws Exception {

        BookDto dto = new BookDto();
        dto.setId(1);
        dto.setTitle("Test Book");

        when(bookService.findById(1)).thenReturn(dto);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void shouldCreateBook() throws Exception {

        CreateBookRequest request =
                TestDataFactory.createBookRequest(1, 1);

        BookDto response = new BookDto();
        response.setId(1);
        response.setTitle("Test Book");

        when(bookService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void shouldUpdateBook() throws Exception {

        UpdateBookRequest request = new UpdateBookRequest();
        request.setTitle("Updated Title");

        BookDto dto = new BookDto();
        dto.setId(1);
        dto.setTitle("Updated Title");

        when(bookService.update(eq(1), any())).thenReturn(dto);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void shouldDeleteBook() throws Exception {

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).delete(1);
    }
}