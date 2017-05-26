package com.weiniu.service;

import java.util.List;

import com.weiniu.entity.IP;

public interface IPService {

	public void insert(IP ip);
	
	public void delete(Integer id);
	
	public List<IP> selectAll();
	
	public IP selectByHostAndPort(IP ip);
	
	public void saveProxy(String host, int port, String from);
}
