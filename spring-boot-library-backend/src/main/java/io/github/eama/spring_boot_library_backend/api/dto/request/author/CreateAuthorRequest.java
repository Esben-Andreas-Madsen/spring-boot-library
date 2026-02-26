package io.github.eama.spring_boot_library_backend.api.dto.request.author;
import lombok.Data;

@Data
public class CreateAuthorRequest {
    //todo include jakarta annotations for variables in EVERY request
    private String name;
    private Integer birthYear;
}
