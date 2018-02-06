package com.zdd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AndroidTestController {

	@RequestMapping("/andtest")
	public String test(){
		return "andtest";
	}
}
