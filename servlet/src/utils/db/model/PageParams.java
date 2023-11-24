package utils.db.model;

/**
 * @projectName: scond_stage
 * @package: utils.db.model
 * @className: PageParams
 * @author: 胡代伟
 * @description: 分页参数
 * @date: 2023/11/10 16:54
 * @version: 1.0
 */
public class PageParams {
    //当前页
    private long pageNo;

    //每页条数
    private long pageSize;

    public PageParams() {
    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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
