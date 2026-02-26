package io.github.eama.spring_boot_library_backend.api.dto.request.book;

import lombok.Data;

import java.util.Set;
import jakarta.validation.constraints.*;

@Data
public class CreateBookRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotNull(message = "Published year is required")
    @Min(value = 1000, message = "Published year must be realistic")
    @Max(value = 2100, message = "Published year must be realistic")
    private Integer publishedYear;

    @NotBlank(message = "ISBN must not be blank")
    @Size(max = 20, message = "ISBN must not exceed 20 characters")
    private String isbn;

    @NotNull(message = "Pages is required")
    @Min(value = 1, message = "Pages must be greater than 0")
    private Integer pages;

    @NotBlank(message = "Language must not be blank")
    @Size(max = 100, message = "Language must not exceed 100 characters")
    private String language;

    // might change to depend on frontend and admin logic
    @NotEmpty(message = "At least one author must be provided")
    private Set<@NotNull(message = "Author ID cannot be null") Integer> authorIds;
}

