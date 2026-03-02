package io.github.eama.spring_boot_library_backend.repository.specification;

public class GenreFilter {

    private String name;
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
