package com.weiniu.dao;

import java.util.List;

import com.weiniu.entity.ProxyIP;

public interface ProxyIPMapper {

	public void insert(ProxyIP ip);
	
	public void delete(Integer id);
	
	public List<ProxyIP> selectAll();
	
	public ProxyIP selectByHostAndPort(ProxyIP ip);
	
}
