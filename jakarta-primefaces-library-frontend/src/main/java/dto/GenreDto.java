package dto;

import java.util.Set;

public class GenreDto {
    private Integer id;
    private String name;

    private Set<Integer> bookIds;

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

    public Set<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Integer> bookIds) {
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
