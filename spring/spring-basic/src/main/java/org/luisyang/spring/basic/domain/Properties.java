package org.luisyang.spring.basic.domain;

public class Properties {

	private String a;
	private String c;
	private String e;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	@Override
	public String toString() {
		return "Properties [a=" + a + ", c=" + c + ", e=" + e + "]";
	}
	
}
