package io.github.eama.spring_boot_library_backend.repository.specification;

import io.github.eama.spring_boot_library_backend.domain.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {

    public static Specification<Author> build(AuthorFilter filter) {

        if (filter == null) {
            return Specification.where((Specification<Author>) null);
        }

        return Specification
                .where(hasName(filter.getName()))
                .and(birthYearGreaterThan(filter.getBirthYearFrom()))
                .and(birthYearLessThan(filter.getBirthYearTo()))
                .and(hasBookId(filter.getBookId()));
    }

    public static Specification<Author> hasName(String name) {
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

    public static Specification<Author> birthYearGreaterThan(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null;
            }
            return cb.greaterThan(root.get("birthYear"), year);
        };
    }

    public static Specification<Author> birthYearLessThan(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null;
            }
            return cb.lessThan(root.get("birthYear"), year);
        };
    }

    public static Specification<Author> hasBookId(Integer bookId) {
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
