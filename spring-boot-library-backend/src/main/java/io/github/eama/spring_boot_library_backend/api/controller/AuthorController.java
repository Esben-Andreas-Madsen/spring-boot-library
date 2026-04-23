package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.author.CreateAuthorRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.author.UpdateAuthorRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.AuthorDto;
import io.github.eama.spring_boot_library_backend.repository.specification.AuthorFilter;
import io.github.eama.spring_boot_library_backend.service.AuthorService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    // ---------- GET AUTHOR BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        AuthorDto found = authorService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }

    // ---------- CREATE AUTHOR ----------
    @PostMapping
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        AuthorDto created = authorService.create(request);

        URI location = URI.create("/authors/" + created.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(created);
    }

    // ---------- UPDATE AUTHOR ----------
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Integer id, @Valid @RequestBody UpdateAuthorRequest request) {
        AuthorDto updated = authorService.update(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    // ---------- DELETE AUTHOR ----------
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // query authors

    @GetMapping
    public ResponseEntity<Page<AuthorDto>> getAuthors(@ParameterObject AuthorFilter filter,
                                                      @PageableDefault(size = 20, sort = "name")
                                                      @ParameterObject Pageable pageable) {
        Page<AuthorDto> foundAuthors = authorService.getAuthors(filter, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundAuthors);
    }

    @GetMapping("/debug")
    public Object debug(Authentication auth) {
        return auth.getAuthorities();
    }
}

