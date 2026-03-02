package io.github.eama.spring_boot_library_backend.api.dto.request.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateGenreRequest {
    @NotBlank(message = "Genre Name must not be blank")
    @Size(max = 50, message = "Genre name must not exceed 50 characters")
    private String name;
}
