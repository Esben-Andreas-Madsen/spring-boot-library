package util;

import org.primefaces.model.FilterMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class FilterMapper<F> {

    private final Map<String, BiConsumer<F, Object>> mappings = new HashMap<>();

    public <T> FilterMapper<F> map(String field, Class<T> type, BiConsumer<F, T> setter) {

        mappings.put(field, (filter, value) -> {

            if (value == null) return;

            T converted = convert(value, type);
            setter.accept(filter, converted);
        });

        return this;
    }

    public void apply(F filter, Map<String, FilterMeta> filterBy) {

        filterBy.forEach((key, meta) -> {

            BiConsumer<F, Object> mapper = mappings.get(key);
            if (mapper == null) return;

            mapper.accept(filter, meta.getFilterValue());
        });
    }

    @SuppressWarnings("unchecked")
    private <T> T convert(Object value, Class<T> type) {

        if (type.isInstance(value)) {
            return (T) value;
        }

        String str = value.toString();

        if (type == String.class) {
            return (T) str;
        }

        if (type == Integer.class) {
            return (T) Integer.valueOf(str);
        }

        if (type == Long.class) {
            return (T) Long.valueOf(str);
        }

        throw new IllegalArgumentException("Unsupported filter type: " + type);
    }
}
