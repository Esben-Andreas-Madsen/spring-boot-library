package io.github.eama.spring_boot_library_backend.repository.specification;

import io.github.eama.spring_boot_library_backend.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> build(BookFilter filter) {

        if (filter == null) {
            return Specification.where((Specification<Book>) null);
        }

        return Specification
                .where(hasTitle(filter.getTitle()))
                .and(publishedYearGreaterThan(filter.getPublishedYearFrom()))
                .and(publishedYearLessThan(filter.getPublishedYearTo()))
                .and(hasIsbn(filter.getIsbn()))
                .and(hasLanguage(filter.getLanguage()))
                .and(pagesGreaterThan(filter.getPagesFrom()))
                .and(pagesLessThan(filter.getPagesTo()))
                .and(publishedYearEquals(filter.getPublishedYear()))
                .and(hasAuthorId(filter.getAuthorId()))
                .and(hasGenreId(filter.getGenreId()));
    }

    public static Specification<Book> hasTitle(String title) {
        return containsIgnoreCase("title", title);
    }

    public static Specification<Book> publishedYearEquals(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null;
            }
            return cb.equal(root.get("publishedYear"), year);
        };
    }

    public static Specification<Book> publishedYearGreaterThan(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get("publishedYear"), year);
        };
    }

    public static Specification<Book> publishedYearLessThan(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("publishedYear"), year);
        };
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return containsIgnoreCase("isbn", isbn);
    }

    public static Specification<Book> hasLanguage(String language) {
        return containsIgnoreCase("language", language);
    }

    public static Specification<Book> pagesGreaterThan(Integer pages) {
        return (root, query, cb) -> {
            if (pages == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get("pages"), pages);
        };
    }

    public static Specification<Book> pagesLessThan(Integer pages) {
        return (root, query, cb) -> {
            if (pages == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("pages"), pages);
        };
    }

    public static Specification<Book> hasAuthorId(Integer authorId) {
        return (root, query, cb) -> {
            if (authorId == null) {
                return null;
            }

            query.distinct(true);

            return cb.equal(
                    root.join("authors").get("id"),
                    authorId
            );
        };
    }

    public static Specification<Book> hasGenreId(Integer genreId) {
        return (root, query, cb) -> {
            if (genreId == null) {
                return null;
            }

            query.distinct(true);

            return cb.equal(
                    root.join("genres").get("id"),
                    genreId
            );
        };
    }

    // helper method
    private static Specification<Book> containsIgnoreCase(String field, String value) {

        return (root, query, cb) -> {

            if (value == null || value.isBlank())
                return null;

            return cb.like(
                    cb.lower(root.get(field)),
                    "%" + value.toLowerCase() + "%"
            );
        };
    }
}