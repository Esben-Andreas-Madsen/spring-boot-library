package io.github.eama.spring_boot_library_backend.api.dto.request.author;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CreateAuthorRequest {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String name;

    @NotNull(message = "Birth year is required")
    @Min(value = 1000, message = "Birth year must be realistic")
    @Max(value = 2100, message = "Birth year must be realistic")
    private Integer birthYear;
}
