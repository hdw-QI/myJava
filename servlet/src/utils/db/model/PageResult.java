package utils.db.model;

import java.util.List;

/**
 * @projectName: scond_stage
 * @package: utils.db.model
 * @className: PageResult
 * @author: 胡代伟
 * @description: 分页结果
 * @date: 2023/11/10 16:54
 * @version: 1.0
 */
public class PageResult<T> {
    //总页数
    private long totalPages;
    //总记录数
    private long totalCounts;

    private long pageNo;

    private long pageSize;
    //分页结果
    private List<T> result;

    public PageResult() {
    }

    public PageResult(long totalPages, long totalCounts, List<T> result,PageParams pageParams) {
        this.totalPages = totalPages;
        this.totalCounts = totalCounts;
        this.result = result;
        this.pageNo=pageParams.getPageNo();
        this.pageSize=pageParams.getPageSize();
    }



    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(long totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
