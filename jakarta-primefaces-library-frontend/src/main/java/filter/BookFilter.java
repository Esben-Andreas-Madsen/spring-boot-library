package filter;

import jakarta.ws.rs.QueryParam;

public class BookFilter {

    @QueryParam("title")
    private String title;

    @QueryParam("publishedYearFrom")
    private Integer publishedYearFrom;

    @QueryParam("publishedYearTo")
    private Integer publishedYearTo;

    @QueryParam("isbn")
    private String isbn;

    @QueryParam("language")
    private String language;

    @QueryParam("pagesFrom")
    private Integer pagesFrom;

    @QueryParam("pagesTo")
    private Integer pagesTo;

    @QueryParam("authorId")
    private Integer authorId;

    @QueryParam("genreId")
    private Integer genreId;

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

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}