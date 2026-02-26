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
                .and(hasAuthorId(filter.getAuthorId()));
    }

    public static Specification<Book> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
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
        return (root, query, cb) -> {
            if (isbn == null || isbn.isBlank()) {
                return null;
            }

            return cb.equal(root.get("isbn"), isbn);
        };
    }

    public static Specification<Book> hasLanguage(String language) {
        return (root, query, cb) -> {
            if (language == null || language.isBlank()) {
                return null;
            }

            return cb.equal(
                    cb.lower(root.get("language")),
                    language.toLowerCase()
            );
        };
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
}