package io.github.eama.spring_boot_library_backend.repository;

import io.github.eama.spring_boot_library_backend.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

