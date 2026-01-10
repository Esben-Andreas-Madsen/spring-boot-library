package io.github.eama.spring_boot_library_backend.api.controller;

import io.github.eama.spring_boot_library_backend.api.dto.request.author.CreateAuthorRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.author.UpdateAuthorRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.AuthorDto;
import io.github.eama.spring_boot_library_backend.api.mapper.AuthorMapper;
import io.github.eama.spring_boot_library_backend.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // ---------- GET ALL AUTHORS ----------
    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll().stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    // ---------- GET AUTHOR BY ID ----------
    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Integer id) {
        return AuthorMapper.toDto(authorService.findById(id));
    }

    // ---------- CREATE AUTHOR ----------
    @PostMapping
    public AuthorDto createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return AuthorMapper.toDto(authorService.create(request));
    }

    // ---------- UPDATE AUTHOR ----------
    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Integer id, @Valid @RequestBody UpdateAuthorRequest request) {
        return AuthorMapper.toDto(authorService.update(id, request));
    }

    // ---------- DELETE AUTHOR ----------
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.delete(id);
    }
}

