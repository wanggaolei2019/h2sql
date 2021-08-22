package com.wanggl.h2sql.base.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页常量
 *
 * @author no one
 */
public interface PageConstants {
    /**
     * 默认页码
     */
    public static final long DEFAULT_PAGE = 1;
    /**
     * 默认显示条数
     */
    public static final long DEFAULT_LIMIT = 10;

    /**
     * 空的list
     */
    public static final List DEFAULT_RESULT = new ArrayList<>();
}
