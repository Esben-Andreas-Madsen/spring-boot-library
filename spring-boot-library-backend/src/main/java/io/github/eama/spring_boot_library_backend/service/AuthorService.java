package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.mapper.AuthorMapper;
import io.github.eama.spring_boot_library_backend.dto.request.author.CreateAuthorRequest;
import io.github.eama.spring_boot_library_backend.dto.request.author.UpdateAuthorRequest;
import io.github.eama.spring_boot_library_backend.domain.Author;
import io.github.eama.spring_boot_library_backend.dto.response.AuthorDto;
import io.github.eama.spring_boot_library_backend.repository.specification.AuthorFilter;
import io.github.eama.spring_boot_library_backend.repository.AuthorRepository;
import io.github.eama.spring_boot_library_backend.repository.specification.AuthorSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    // ---------- READ ----------

    @Transactional(readOnly = true)
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public AuthorDto findById(Integer id) {
        return authorMapper.toDto(authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found")));
    }

    public Page<AuthorDto> getAuthors(AuthorFilter filter, Pageable pageable) {

        Specification<Author> spec = AuthorSpecification.build(filter);


        return authorRepository.findAll(spec, pageable).map(authorMapper::toDto);
    }


    // ---------- CREATE ----------

    public AuthorDto create(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBirthYear(request.getBirthYear());

        return authorMapper.toDto(authorRepository.save(author));
    }

    // ---------- UPDATE ----------

    public AuthorDto update(Integer id, UpdateAuthorRequest request) {
        Author author = authorMapper.toEntity(findById(id));

        author.setName(request.getName());
        author.setBirthYear(request.getBirthYear());

        return authorMapper.toDto(author);
    }

    // ---------- DELETE ----------

    public void delete(Integer id) {
        Author author = authorMapper.toEntity(findById(id));
        authorRepository.delete(author);
    }
}

