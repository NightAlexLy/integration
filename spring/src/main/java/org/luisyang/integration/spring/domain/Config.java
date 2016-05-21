package org.luisyang.integration.spring.domain;

/**
 * @author apple
 *
 */
public class Config {

	private String ip;

	private String host;

	private String hostanme;

	public Config(String ip, String host, String hostanme) {
		super();
		this.ip = ip;
		this.host = host;
		this.hostanme = hostanme;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHostanme() {
		return hostanme;
	}

	public void setHostanme(String hostanme) {
		this.hostanme = hostanme;
	}

	@Override
	public String toString() {
		return "Config [ip=" + ip + ", host=" + host + ", hostanme=" + hostanme + "]";
	}

}
