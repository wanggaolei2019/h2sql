package com.wanggl.h2sql.base.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 分页请求公共参参数
 *
 * @author wanggl
 */
@ApiModel("分页查询")
public class PageReq<T> implements Serializable {
    private static final long serialVersionUID = 7007975362965519776L;

    @ApiModelProperty(value = "页码,默认为1", example = "1", position = 1)
    private long pageNo = PageConstants.DEFAULT_PAGE;

    @ApiModelProperty(value = "行数,默认为10", example = "10", position = 2)
    private long pageSize = PageConstants.DEFAULT_LIMIT;

    @ApiModelProperty(value = "业务参数", position = 3)
    private T params;

    public Pageable getPage() {
        return PageRequest.of((int) pageNo - 1, (int) pageSize, Sort.by("createTime").descending());
    }

    @JsonIgnore
    public Long getOffset() {
        return (pageNo - 1) * pageSize;
    }

    @JsonIgnore
    public Long getLimit() {
        return pageSize;
    }

    public static <T> PageReq<T> of(T t) {
        PageReq<T> pageReq = new PageReq<>();
        pageReq.setParams(t);
        return pageReq;
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

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
