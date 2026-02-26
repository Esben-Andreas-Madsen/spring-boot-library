package io.github.eama.spring_boot_library_backend.repository.specification;

import io.github.eama.spring_boot_library_backend.api.dto.response.AuthorDto;

import java.util.Set;

public class BookFilter {

    private String title;
    private Integer publishedYearFrom;
    private Integer publishedYearTo;
    private Integer publishedYear;
    private String isbn;
    private String language;
    private Integer pagesFrom;
    private Integer pagesTo;
    private Integer authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishedYearFrom() {
        return publishedYearFrom;
    }

    public void setPublishedYearFrom(Integer publishedYearFrom) {
        this.publishedYearFrom = publishedYearFrom;
    }

    public Integer getPublishedYearTo() {
        return publishedYearTo;
    }

    public void setPublishedYearTo(Integer publishedYearTo) {
        this.publishedYearTo = publishedYearTo;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPagesFrom() {
        return pagesFrom;
    }

    public void setPagesFrom(Integer pagesFrom) {
        this.pagesFrom = pagesFrom;
    }

    public Integer getPagesTo() {
        return pagesTo;
    }

    public void setPagesTo(Integer pagesTo) {
        this.pagesTo = pagesTo;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
