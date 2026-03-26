package dto;

import java.util.List;

public class PageDto<T> {

    private List<T> content;
    private PageMeta page;

    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }

    public PageMeta getPage() { return page; }
    public void setPage(PageMeta page) { this.page = page; }

    public long getTotalElements() {
        return page != null ? page.getTotalElements() : 0;
    }
}
