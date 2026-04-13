package bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.Resource;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@SessionScoped
public class ThemeBean implements Serializable {

    private String theme = "saga-blue";

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Map<String,String> getThemes() {

        Map<String,String> map = new LinkedHashMap<>();

        map.put("Saga Blue", "saga-blue");
        map.put("Arya Blue", "arya-blue");
        map.put("Vela Blue", "vela-blue");

        return map;
    }
}