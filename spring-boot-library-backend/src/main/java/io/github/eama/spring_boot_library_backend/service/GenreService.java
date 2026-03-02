package io.github.eama.spring_boot_library_backend.service;

import io.github.eama.spring_boot_library_backend.api.dto.request.genre.CreateGenreRequest;
import io.github.eama.spring_boot_library_backend.api.dto.request.genre.UpdateGenreRequest;
import io.github.eama.spring_boot_library_backend.api.dto.response.GenreDto;
import io.github.eama.spring_boot_library_backend.api.exception.genre.GenreNotFoundException;
import io.github.eama.spring_boot_library_backend.domain.Genre;
import io.github.eama.spring_boot_library_backend.mapper.GenreMapper;
import io.github.eama.spring_boot_library_backend.repository.GenreRepository;
import io.github.eama.spring_boot_library_backend.repository.specification.GenreFilter;
import io.github.eama.spring_boot_library_backend.repository.specification.GenreSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    public GenreService(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper = genreMapper;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public GenreDto findById(Integer id) {
        return genreMapper.toDto(genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id)));
    }

    public Page<GenreDto> getGenres(GenreFilter filter, Pageable pageable) {
        Specification<Genre> spec = GenreSpecification.build(filter);

        return genreRepository.findAll(spec, pageable).map(genreMapper::toDto);
    }

    public GenreDto create(CreateGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());

        return genreMapper.toDto(genreRepository.save(genre));
    }

    public GenreDto update(Integer id, UpdateGenreRequest request) {
        Genre genre = genreMapper.toEntity(findById(id));

        genre.setName(request.getName());

        return genreMapper.toDto(genre);
    }

    public void delete(Integer id) {
        Genre genre = genreMapper.toEntity(findById(id));
        genreRepository.delete(genre);
    }


}
