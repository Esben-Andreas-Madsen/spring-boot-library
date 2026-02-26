package io.github.eama.spring_boot_library_backend.api.dto.request.book;

import lombok.Data;

import java.util.Set;
import jakarta.validation.constraints.*;

@Data
public class UpdateBookRequest {

    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Min(value = 1000, message = "Published year must be realistic")
    @Max(value = 2100, message = "Published year must be realistic")
    private Integer publishedYear;

    @Size(max = 20, message = "ISBN must not exceed 20 characters")
    private String isbn;

    @Min(value = 1, message = "Pages must be greater than 0")
    private Integer pages;

    @Size(max = 100, message = "Language must not exceed 100 characters")
    private String language;

    private Set<@NotNull(message = "Author ID cannot be null") Integer> authorIds;
}