package converter;

import dto.AuthorDto;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import service.AuthorService;

import java.util.List;

@FacesConverter(value = "authorConverter", managed = true)
public class AuthorConverter implements Converter<AuthorDto> {

    @Inject
    private AuthorService authorService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, AuthorDto value) {
        if (value == null) return "";
        return String.valueOf(value.getId());
    }

    @Override
    public AuthorDto getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        Long id = Long.valueOf(value);

        // simplest: fetch single author
        return authorService.getAuthorsByIds(List.of(id))
                .stream()
                .findFirst()
                .orElse(null);
    }
}
