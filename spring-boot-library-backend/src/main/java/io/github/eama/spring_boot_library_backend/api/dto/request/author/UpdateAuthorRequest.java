package io.github.eama.spring_boot_library_backend.api.dto.request.author;

import lombok.Data;

@Data
public class UpdateAuthorRequest {
    private String name;
    private Integer birthYear;
}
