package com.wanggl.h2sql.base.page;

import cn.hutool.extra.cglib.CglibUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;

/**
 * 分页响应公共参参数
 *
 * @author wanggl
 */
@ApiModel("分页数据")
public class PageResp<T> implements Serializable {
    private static final long serialVersionUID = 5780389960005399972L;

    @ApiModelProperty(value = "当前页数", position = 1)
    private long pageNo;

    @ApiModelProperty(value = "每页条数", position = 2)
    private long pageSize;

    @ApiModelProperty(value = "总条目数", position = 3)
    private long total;

    @ApiModelProperty(value = "当前页数据", position = 4)
    private List<T> data;

    public PageResp() {
    }

    public PageResp(long pageNo, long pageSize, List<T> list, long total) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.data = list;
        this.total = total;
    }

    public static <T> PageResp<T> of(long pageNo, long pageSize, List<T> result, long total) {
        return new PageResp<>(pageNo, pageSize, result, total);
    }


    public static <T> PageResp<T> of(Page<T> page) {
        return new PageResp<T>(page.getNumber() + 1, page.getSize(), page.getContent(), page.getTotalElements());
    }

    public static <T, E> PageResp<E> of(Page<T> page, Supplier<E> target) {
        return new PageResp<E>(page.getNumber() + 1, page.getSize(), CglibUtil.copyList(page.getContent(), target), page.getTotalElements());
    }

    public static <T> PageResp<T> ofEmpty(PageReq<T> pageReq) {
        return new PageResp<T>(pageReq.getPageNo(), pageReq.getPageSize(), PageConstants.DEFAULT_RESULT, 0);
    }

    public static <T, E> PageResp<E> ofEmpty(PageReq<T> pageReq, Class<E> temp) {
        return new PageResp<E>(pageReq.getPageNo(), pageReq.getPageSize(), PageConstants.DEFAULT_RESULT, 0);
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
