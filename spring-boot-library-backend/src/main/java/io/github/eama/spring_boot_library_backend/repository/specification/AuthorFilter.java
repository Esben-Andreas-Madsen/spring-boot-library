package io.github.eama.spring_boot_library_backend.repository.specification;

public class AuthorFilter {

    private String name;
    private Integer birthYearFrom;
    private Integer birthYearTo;
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
