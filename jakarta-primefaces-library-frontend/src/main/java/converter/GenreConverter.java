package converter;

import dto.GenreDto;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import service.GenreService;

import java.util.List;

@FacesConverter(value = "genreConverter", managed = true)
public class GenreConverter implements Converter<GenreDto> {

    @Inject
    private GenreService genreService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, GenreDto value) {
        return value != null ? String.valueOf(value.getId()) : "";
    }

    @Override
    public GenreDto getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isBlank()) return null;

        Long id = Long.valueOf(value);

        return genreService.getGenresByIds(List.of(id))
                .stream()
                .findFirst()
                .orElse(null);
    }
}
