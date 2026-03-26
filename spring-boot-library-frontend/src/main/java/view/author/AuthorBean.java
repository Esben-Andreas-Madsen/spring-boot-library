package view.author;

import dto.AuthorDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.AuthorService;

@RequestScoped
@Named
public class AuthorBean {

    @Inject
    AuthorService authorService;

    private Integer authorId;
    private AuthorDto author;

    public void loadAuthor() {
        if (authorId != null) {
            author = authorService.getAuthor(authorId);
        }
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
