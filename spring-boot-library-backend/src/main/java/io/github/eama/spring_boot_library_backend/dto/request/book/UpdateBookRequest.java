package io.github.eama.spring_boot_library_backend.dto.request.book;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateBookRequest {
    private String title;
    private Integer publishedYear;
    private String isbn;
    private Integer pages;
    private String language;
    private Set<Integer> authorIds;
}
