package com.msj.goods.common.web.base;


import java.util.Map;

/**
 * Created by Administrator on 2018/5/12/012.
 */
public class PageModelParams {

    private int pageNum;
    private int pageSize;
    private Map<String,Object> condition;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
