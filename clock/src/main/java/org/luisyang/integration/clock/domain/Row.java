package org.luisyang.integration.clock.domain;

/**
 * 一行数据
 * 
 * @author night_000
 */
public class Row {

	/**
	 * 数组
	 */
	private String[] cell;
	
	/**
	 * 名字
	 */
	private String id;

	public String[] getCell() {
		return cell;
	}

	public void setCell(String[] cell) {
		this.cell = cell;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		return other.getCell()[1].equals(this.getCell()[1]);
	}
}
