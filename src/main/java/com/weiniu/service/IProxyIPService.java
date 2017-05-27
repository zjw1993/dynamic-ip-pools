package com.weiniu.service;

import java.util.List;

import com.weiniu.entity.ProxyIP;

public interface IProxyIPService {

	public void insert(ProxyIP ip);
	
	public void delete(Integer id);
	
	public List<ProxyIP> selectAll();
	
	public ProxyIP selectByHostAndPort(ProxyIP ip);
	
	public void saveProxy(String host, int port, String from);
	
	/**
	 * 随机获取一个可用ip
	 * @return
	 */
	public ProxyIP getAProxy();
}
