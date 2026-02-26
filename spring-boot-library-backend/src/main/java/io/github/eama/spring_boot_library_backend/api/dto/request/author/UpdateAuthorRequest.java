package io.github.eama.spring_boot_library_backend.api.dto.request.author;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UpdateAuthorRequest {

    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String name;

    @Min(value = 1000, message = "Birth year must be realistic")
    @Max(value = 2100, message = "Birth year must be realistic")
    private Integer birthYear;
}
