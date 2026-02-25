package io.github.eama.spring_boot_library_backend.dto.response;

import java.util.Set;

public class AuthorDto {

    private Integer id;
    private String name;
    private Integer birthYear;

    // avoid bidirectional nesting --> infinite recursion
    // a design choice
    private Set<Integer> bookIds;

    /// getters setters

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
}
