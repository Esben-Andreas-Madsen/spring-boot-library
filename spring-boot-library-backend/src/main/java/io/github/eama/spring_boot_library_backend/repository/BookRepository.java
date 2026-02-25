package io.github.eama.spring_boot_library_backend.repository;

import io.github.eama.spring_boot_library_backend.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @EntityGraph(attributePaths = "authors")
    List<Book> findAll();

    @EntityGraph(attributePaths = "authors")
    Optional<Book> findById(Integer id);
}


