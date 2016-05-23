package org.luisyang.integration.clock.domain;

import java.util.List;

public class ClockEntity {

	/**
	 * 总页数
	 */
	private int total;
	
	/**
	 * 当前页数
	 */
	private int page;
	
	/**
	 * 总条数
	 */
	private int records;
	
	/**
	 * 总记录
	 */
	private List<Row> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

}
