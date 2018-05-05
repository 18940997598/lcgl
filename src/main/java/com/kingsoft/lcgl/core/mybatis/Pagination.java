package com.kingsoft.lcgl.core.mybatis;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
public class Pagination<E> extends RowBounds implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总记录数
	 */
	private Long totalRecord;
	
	/**
	 * 每页记录数
	 */
	private Long pageSize;
	
	/**
	 * 当前页
	 */
	private Long currentPage;
	
	/**
	 * 排序对象
	 */
	private Sort sort;
	
	/**
	 * 列表对象
	 */
	private List<E> content;

	/**
	 * 无参构造方法
	 */
	public Pagination() {
		this.pageSize = 20L;
		this.currentPage = 1L;
	}

	/**
	 * 带初始化分页大小和当前页构造方法
	 * @param pageSize
	 * @param currentPage 
	 */
	public Pagination(Long pageSize, Long currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	/**
	 * 取得总记录数
	 * @return 
	 */
	public Long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置总记录数
	 * @param totalRecord 
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		if(currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
	}

	/**
	 * 取得每页记录数
	 * @return 
	 */
	public Long getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 * @param pageSize 
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 取得当前页
	 * @return 
	 */
	public Long getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 * @param currentPage 
	 */
	public void setCurrentPage(long currentPage) {
		if(currentPage < 1) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	/**
	 * 取得排序对象
	 * @return 
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * 设置排序对象
	 * @param sort 
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * 取得查询结果列表对象
	 * @return 
	 */
	public List<E> getContent() {
		return content;
	}

	/**
	 * 设置查询结果列表对象
	 * @param content 
	 */
	public void setContent(List<E> content) {
		this.content = content;
	}
	
	/**
	 * 取得总页数
	 * @return 
	 */
	public Long getTotalPage() {
		return (long) Math.ceil(totalRecord /(double) pageSize);
	}
	
	/**
	 * 取得Limit
	 * @return 
	 */
	@Override
	public int getLimit() {
		return pageSize.intValue();
	}
	
	/**
	 * 取得Offset
	 * @return 
	 */
	@Override
	public int getOffset() {
		return (currentPage.intValue() - 1) * pageSize.intValue();
	}

	@Override
	public String toString() {
		return "Pagination{" + "totalRecord=" + totalRecord + ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", sort=" + sort + ", content=" + content + '}';
	}
	
}
