package com.mlk.home.base;

import com.mlk.home.page.PageConfig;

import javax.persistence.Transient;
import java.io.Serializable;

public class BaseSearchModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 描述: 页数
     */
    @Transient
    private Integer page;

    /**
     * 描述: 每页记录
     */
    @Transient
    private Integer rows;

    /**
     * 描述: 排序条件(排序字段)
     */
    @Transient
    private String sidx;

    /**
     * 描述: 排序方式
     */
    @Transient
    private String sord;

    /**
     * 描述: 编辑标识 0/null新增 1修改
     */
    @Transient
    private Long editFlag;

    /**
     * 描述：操作类别 add：新增 edit：编辑
     */
    @Transient
    private String oper;

    public Integer getPage() {
        // 获取页号，判断页号是否合法，如不合法设置为
        if (this.page == null || this.page.longValue() <= 1) {
            page = PageConfig.PAGE_NO;
        }
        return page;
    }

    public void setPage(Integer pageIndex) {
        this.page = pageIndex;
    }


    public Integer getRows() {
        // 获取每页记录数，判断每页记录数据是否合法，如不合法设置为应用程序参数
        if (this.rows == null || this.rows.longValue() <= 0) {
            rows = PageConfig.PAGE_ROW_COUNT;
        }
        return rows;
    }


    public void setRows(Integer pageSize) {
        this.rows = pageSize;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Long getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(Long editFlag) {
        this.editFlag = editFlag;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}

