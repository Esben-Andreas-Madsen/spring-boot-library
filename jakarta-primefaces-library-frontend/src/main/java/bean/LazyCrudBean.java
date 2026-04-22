package bean;

import jakarta.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;

import java.io.Serializable;
import java.util.List;

public abstract class LazyCrudBean<T, F> implements Serializable {

    protected LazyDataModel<T> dataModel;

    protected F filter;

    protected T selected;

    protected T create;

    private List<T> selectedItems;

    @PostConstruct
    public void initCrud() {
        filter = newFilter();
        dataModel = newLazyModel(filter);
        create = newEntity();
    }

    protected abstract LazyDataModel<T> newLazyModel(F filter);

    protected abstract F newFilter();

    protected abstract T newEntity();

    protected abstract void createEntity(T entity);

    protected abstract void updateEntity(T entity);

    protected abstract void deleteEntity(T entity);

    public void save() {

        T entity;

        if (selected != null && getId(selected) != null) {
            entity = selected;   // edit
        } else {
            entity = create;     // create
        }

        if (getId(entity) == null) {
            createEntity(entity);
        } else {
            updateEntity(entity);
        }

        create = newEntity();
        selected = null;
    }

    public void openNew() {
        selected = null;
        create = newEntity();
    }


    public void deleteSelected() {

        // multi-delete
        if (selectedItems != null && !selectedItems.isEmpty()) {
            selectedItems.forEach(this::deleteEntity);
            selectedItems = null;
        }
        // fallback single-delete
        else if (selected != null) {
            deleteEntity(selected);
            selected = null;
        }
    }

    protected abstract Object getId(T entity);

    public LazyDataModel<T> getDataModel() {
        return dataModel;
    }

    public F getFilter() {
        return filter;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public T getCreate() {
        return create;
    }

    public void setCreate(T create) {
        this.create = create;
    }

    public List<T> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<T> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public boolean hasSelectedItems() {
        return selectedItems != null && !selectedItems.isEmpty();
    }

    public T getCurrent() {
        return selected != null ? selected : create;
    }
}
