package bing.common;

import lombok.Data;

import java.util.List;

/**
 * Page
 *
 * @author: IceWee
 * @date: 2019.1.2
 */
@Data
public class Page<M> {

    private int pageNo = 1;//默认为显示第一页

    private int pageSize = 10; //每一页显示的数量

    private int totalCount;

    private volatile int totalPages;

    private volatile int index;

    private List<M> data;

    public int getIndex() {
        index = (pageNo - 1) * pageSize;
        return index;
    }

    public int getTotalPages() {
        if (totalCount % pageSize == 0) {
            totalPages = totalCount / pageSize;
        } else {
            totalPages = (totalCount / pageSize) + 1;
        }
        return totalPages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<M> getData() {
        return data;
    }

    public void setData(List<M> data) {
        this.data = data;
    }
}
