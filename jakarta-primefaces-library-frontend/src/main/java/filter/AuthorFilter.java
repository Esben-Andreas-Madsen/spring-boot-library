package filter;

import jakarta.ws.rs.QueryParam;

public class AuthorFilter {

    @QueryParam("name")
    private String name;

    @QueryParam("birthYearFrom")
    private Integer birthYearFrom;

    @QueryParam("birthYearTo")
    private Integer birthYearTo;

    @QueryParam("bookId")
    private Integer bookId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYearFrom() {
        return birthYearFrom;
    }

    public void setBirthYearFrom(Integer birthYearFrom) {
        this.birthYearFrom = birthYearFrom;
    }

    public Integer getBirthYearTo() {
        return birthYearTo;
    }

    public void setBirthYearTo(Integer birthYearTo) {
        this.birthYearTo = birthYearTo;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
