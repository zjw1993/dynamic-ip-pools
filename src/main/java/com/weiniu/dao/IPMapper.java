package com.weiniu.dao;

import java.util.List;

import com.weiniu.entity.IP;

public interface IPMapper {

	public void insert(IP ip);
	
	public void delete(Integer id);
	
	public List<IP> selectAll();
	
	public IP selectByHostAndPort(IP ip);
	
}
