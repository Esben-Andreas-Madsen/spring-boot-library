package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.dto.request.author.CreateAuthorRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.author.UpdateAuthorRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // ---------- READ ----------

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // ---------- CREATE ----------

    public Author create(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBirthYear(request.getBirthYear());

        return authorRepository.save(author);
    }

    // ---------- UPDATE ----------

    public Author update(Integer id, UpdateAuthorRequest request) {
        Author author = findById(id);

        author.setName(request.getName());
        author.setBirthYear(request.getBirthYear());

        return author;
    }

    // ---------- DELETE ----------

    public void delete(Integer id) {
        Author author = findById(id);
        authorRepository.delete(author);
    }
}

