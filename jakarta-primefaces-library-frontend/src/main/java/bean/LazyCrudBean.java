package bean;

import jakarta.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;

import java.io.Serializable;

public abstract class LazyCrudBean<T, F> implements Serializable {

    protected LazyDataModel<T> dataModel;

    protected F filter;

    protected T selected;

    protected T create;

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


    public void deleteSelected() {
        if (selected != null) {
            deleteEntity(selected);
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
}
