package com.weiniu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiniu.entity.ProxyIP;
import com.weiniu.service.IProxyIPService;

@Controller
@RequestMapping("/proxy")
public class ProxyIPController {

	@Autowired
	private IProxyIPService proxyIPService;
	
	@ResponseBody
	@RequestMapping("/stat")
	public Map<String, Object> stat(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ProxyIP> list = proxyIPService.selectAll();
		
		map.put("data", list);
		map.put("count", list.size());
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ProxyIP> list = proxyIPService.selectAll();
		List<String> ipList = new ArrayList<String>();
		for(ProxyIP ip: list) {
			String str = ip.getHost() + ":" + ip.getPort();
			ipList.add(str);
		}
		
		map.put("data", ipList);
		map.put("count", ipList.size());
		
		return map;
	}
	
	
}
