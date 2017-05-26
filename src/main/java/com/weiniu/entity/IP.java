package com.weiniu.entity;

public class IP {

	private Integer id;
	private String host;
	private int port;
	private String location;
	private String comeFrom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IP [id=");
		builder.append(id);
		builder.append(", host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", location=");
		builder.append(location);
		builder.append(", from=");
		builder.append(comeFrom);
		builder.append("]");
		return builder.toString();
	}

	
	
}