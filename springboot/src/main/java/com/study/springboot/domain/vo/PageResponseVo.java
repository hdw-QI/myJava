package com.study.springboot.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 胡代伟
 * @description 分页结果统一响应
 * @date 2023-12-29 12:52
 */
//@ApiModel("分页结果统一响应")
@Data
public class PageResponseVo<T> {
//    @ApiModelProperty("响应码")
    private int code;
//    @ApiModelProperty("提示信息")
    private String msg;
//    @ApiModelProperty("总页数")
    private Long count;
//    @ApiModelProperty("分页数据")
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

    public static <T> PageResponseVo<T> success(String msg, Long count, List<T> data) {
        PageResponseVo<T> tPageResponse = new PageResponseVo<>();
        tPageResponse.setCode(0);
        tPageResponse.setMsg(msg);
        tPageResponse.setCount(count);
        tPageResponse.setData(data);
        return tPageResponse;
    }

    public static <T> PageResponseVo<T> fail(String msg, Long count, List<T> data) {
        PageResponseVo<T> tPageResponse = new PageResponseVo<>();
        tPageResponse.setCode(1);
        tPageResponse.setMsg(msg);
        tPageResponse.setCount(count);
        tPageResponse.setData(data);
        return tPageResponse;
    }
}
