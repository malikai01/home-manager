package com.mlk.home.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>
 * 分页工具类
 * </p>
 */
public class PageInfo<T> implements Serializable {
    @Override
	public String toString() {
		return "PageInfo [page=" + page + ", total=" + total + ", pageSize="
				+ pageSize + ", records=" + records + "]";
	}

	private static final long serialVersionUID = 1L;
    
    private int page;// 当前页
    private long total;// 总页数
    private int pageSize;// 每页显示多少
    private long records;// 总记录数
    private List<T> rows;// 记录

    public PageInfo(int page, int pageSize, long records, List<T> rows) {
        this.page = page;
        this.pageSize = pageSize;
        this.setTotal(records);//设置总页数
        this.rows = rows;
    }

    public PageInfo() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long records) {
        this.records = records;
        if (this.pageSize > 0) {
            this.total = (int) (records / (long) this.pageSize + (long) (records % (long) this.pageSize == 0L ? 0 : 1));
        } else {
            this.total = 0;
        }
    }

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

    
  
}
