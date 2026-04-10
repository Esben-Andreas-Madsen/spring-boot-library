package dto;

import java.util.Set;

public class AuthorDto {

    private Integer id;
    private String name;
    private Integer birthYear;

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

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Set<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", bookIds=" + bookIds +
                '}';
    }
}
