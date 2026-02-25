package io.github.eama.spring_boot_library_backend.repository.specification;

import io.github.eama.spring_boot_library_backend.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    private static Specification<Book> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null :
                        cb.like(cb.lower(root.get("title")),
                                "%" + title.toLowerCase() + "%");
    }

    private static Specification<Book> hasAuthor(Integer authorId) {
        return (root, query, cb) ->
                authorId == null ? null :
                        cb.equal(root.join("authors").get("id"), authorId);
    }

    private static Specification<Book> yearGreaterThan(Integer year) {
        return (root, query, cb) ->
                year == null ? null :
                        cb.greaterThan(root.get("year"), year);
    }

    private static Specification<Book> yearLessThan(Integer year) {
        return (root, query, cb) ->
                year == null ? null :
                        cb.lessThan(root.get("year"), year);
    }
}