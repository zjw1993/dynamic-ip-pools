package com.weiniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weiniu.dao.ProxyIPMapper;
import com.weiniu.entity.ProxyIP;
import com.weiniu.service.IProxyIPService;
import com.weiniu.utils.ProxyUtil;

@Service("proxyIPService")
public class ProxyIPServiceImpl implements IProxyIPService{

	@Autowired
	private ProxyIPMapper proxyIPMapper;
	
	@Override
	public void insert(ProxyIP ip) {
		if(ip != null
				&& !StringUtils.isEmpty(ip.getHost())
				&& 0 != ip.getPort()
				&& null == proxyIPMapper.selectByHostAndPort(ip)) {
			proxyIPMapper.insert(ip);
		}
	}

	@Override
	public void delete(Integer id) {
		if(null != id) {
			proxyIPMapper.delete(id);
		}
	}
	
	@Override
	public List<ProxyIP> selectAll() {
		List<ProxyIP> list = proxyIPMapper.selectAll();
		return list;
	}

	@Override
	public ProxyIP selectByHostAndPort(ProxyIP ip) {
		if(ip != null
				&& !StringUtils.isEmpty(ip.getHost())
				&& 0 != ip.getPort()) {
			return proxyIPMapper.selectByHostAndPort(ip);
		}
		return null;
	}
	
	@Override
	public void saveProxy(String host, int port, String from) {
    	ProxyIP ip = new ProxyIP();
    	ip.setHost(host);
    	ip.setPort(port);
    	ip.setComeFrom(from);
    	insert(ip);
	}
	
	@Override
	public ProxyIP getAProxy(){
		List<ProxyIP> list = selectAll();
		ProxyIP ip;
		while(list.size() > 0) {
			int randomInt = (int) Math.floor(Math.random() * list.size());
			ip = list.get(randomInt);
			if(ProxyUtil.checkProxy(ip)) {
				return ip;
			} else {
				delete(ip.getId());
				list.remove(randomInt);
			}
		}
		return null;
	}

}
