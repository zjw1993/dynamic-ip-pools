package com.weiniu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weiniu.cralwer.KDLProxyIPCralwer;

@Controller
@RequestMapping("/ip")
public class IPController {

	@Autowired
	private KDLProxyIPCralwer kDLProxyIPCralwer;
	
	@RequestMapping("/start")
	public void start(){
		System.out.println("21312312312");
		kDLProxyIPCralwer.run();
	}
	
	
}
