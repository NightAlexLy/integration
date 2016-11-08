package org.luisyang.integration.tools.domian;

/**
 * 全局Sequence
 * 
 * @author ly
 */
public class Sequence {

	private String name;
	private int current_value;
	private int increment;

	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(int current_value) {
		this.current_value = current_value;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	@Override
	public String toString() {
		return "Sequence [name=" + name + ", current_value=" + current_value + ", increment=" + increment + "]";
	}

}
