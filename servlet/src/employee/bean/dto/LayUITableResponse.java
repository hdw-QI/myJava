package employee.bean.dto;

import java.util.List;

/**
 * @projectName: git
 * @package: employee.bean
 * @className: LayUITableResponse
 * @author: 胡代伟
 * @description: layui的表格默认规定的数据格式
 * @date: 2023/11/29 19:22
 * @version: 1.0
 */
public class LayUITableResponse<T> {
    private int code;
    private String msg;
    private Long count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
