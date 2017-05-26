package com.weiniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weiniu.dao.IPMapper;
import com.weiniu.entity.IP;
import com.weiniu.service.IPService;

@Service("iPService")
public class IPServiceImpl implements IPService{

	@Autowired
	private IPMapper iPMapper;
	
	@Override
	public void insert(IP ip) {
		if(ip != null
				&& !StringUtils.isEmpty(ip.getHost())
				&& 0 != ip.getPort()
				&& null == iPMapper.selectByHostAndPort(ip)) {
			iPMapper.insert(ip);
		}
	}

	@Override
	public void delete(Integer id) {
		if(null != id) {
			iPMapper.delete(id);
		}
	}
	
	@Override
	public List<IP> selectAll() {
		List<IP> list = iPMapper.selectAll();
		return list;
	}

	@Override
	public IP selectByHostAndPort(IP ip) {
		if(ip != null
				&& !StringUtils.isEmpty(ip.getHost())
				&& 0 != ip.getPort()) {
			return iPMapper.selectByHostAndPort(ip);
		}
		return null;
	}
	
	public void saveProxy(String host, int port, String from) {
    	IP ip = new IP();
    	ip.setHost(host);
    	ip.setPort(port);
    	ip.setComeFrom(from);
    	insert(ip);
	}

}
