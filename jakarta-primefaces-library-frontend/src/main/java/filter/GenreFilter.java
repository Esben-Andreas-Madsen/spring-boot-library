package filter;

import jakarta.ws.rs.QueryParam;

public class GenreFilter {

    @QueryParam("name")
    private String name;

    @QueryParam("bookId")
    private Integer bookId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
