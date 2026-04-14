package dto;

import java.util.Set;

public class GenreDto {
    private Integer id;
    private String name;

    private Set<Long> bookIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Long> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookIds=" + bookIds +
                '}';
    }
}
