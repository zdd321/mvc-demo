package com.zdd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketTestController {

	@RequestMapping("/websockettest")
	public String execute(){
		return "websocket";
	}
}
