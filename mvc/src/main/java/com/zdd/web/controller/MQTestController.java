package com.zdd.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdd.config.rabbit.Productor;

@Controller
public class MQTestController {

	@Resource
	private Productor productor;
	@RequestMapping("/mqtest")
	public String test(){
		productor.sendMessage("haha");
		return "index";
	}
}
