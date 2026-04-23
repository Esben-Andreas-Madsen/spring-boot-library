package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.genre.CreateGenreRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.genre.UpdateGenreRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.GenreDto;
import io.github.eama.spring_boot_library_backend.repository.specification.GenreFilter;
import io.github.eama.spring_boot_library_backend.service.GenreService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id) {
        GenreDto found = genreService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }

    @PostMapping
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<GenreDto> createGenre(@Valid @RequestBody CreateGenreRequest request) {
        GenreDto created = genreService.create(request);

        URI location = URI.create("/genres/" + created.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Integer id, @Valid @RequestBody UpdateGenreRequest request) {
        GenreDto updated = genreService.update(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        genreService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<GenreDto>> getGenres(@ParameterObject GenreFilter filter,
                                                    @PageableDefault(size = 20, sort = "name")
                                                    @ParameterObject Pageable pageable) {

        Page<GenreDto> foundGenres = genreService.getGenres(filter, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundGenres);
    }
}
