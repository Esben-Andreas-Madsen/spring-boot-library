package datamodels;

import dto.PageDto;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class AbstractLazyDataModel<T, F> extends LazyDataModel<T> {

    protected F filter;

    protected abstract PageDto<T> fetch(int page, int size, F filter, List<String> sort);

    protected abstract F newFilter();

    public AbstractLazyDataModel(F filter) {
        this.filter = filter;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return getRowCount();
    }

    @Override
    public List<T> load(
            int first,
            int pageSize,
            Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filterBy) {

        int page = first / pageSize;

        applyFilters(filterBy);

        List<String> sort = buildSort(sortBy);

        PageDto<T> result = fetch(page, pageSize, filter, sort);

        setRowCount((int) result.getTotalElements());

        return result.getContent();
    }

    private List<String> buildSort(Map<String, SortMeta> sortBy) {

        if (sortBy == null || sortBy.isEmpty()) {
            return List.of();
        }

        return sortBy.values().stream()
                .sorted(Comparator.comparingInt(SortMeta::getPriority))
                .filter(meta -> meta.getOrder() != SortOrder.UNSORTED)
                .map(meta -> meta.getField() + "," +
                        (meta.getOrder() == SortOrder.DESCENDING ? "desc" : "asc"))
                .toList();
    }

    private void applyFilters(Map<String, FilterMeta> filterBy) {

        filterBy.forEach((key, meta) -> {

            Object value = meta.getFilterValue();
            if (value == null) return;

            try {
                Field field = filter.getClass().getDeclaredField(key);
                field.setAccessible(true);

                if (field.getType().equals(Integer.class)) {
                    field.set(filter, Integer.valueOf(value.toString()));
                } else {
                    field.set(filter, value.toString());
                }

            } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        });
    }
}
