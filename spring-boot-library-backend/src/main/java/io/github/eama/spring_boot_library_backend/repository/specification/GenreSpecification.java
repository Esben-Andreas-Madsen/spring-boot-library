package io.github.eama.spring_boot_library_backend.repository.specification;

import io.github.eama.spring_boot_library_backend.domain.Genre;
import org.springframework.data.jpa.domain.Specification;

public class GenreSpecification {

    public static Specification<Genre> build(GenreFilter filter) {
        if (filter == null) {
            return Specification.where((Specification<Genre>) null);
        }

        return Specification
                .where(hasName(filter.getName()))
                .and(hasBookId(filter.getBookId()));
    }

    public static Specification<Genre> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Genre> hasBookId(Integer bookId) {
        return (root, query, cb) -> {
            if (bookId == null) {
                return null;
            }

            query.distinct(true);

            return cb.equal(
                    root.join("books").get("id"),
                    bookId
            );
        };
    }
}
