package com.zdd.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {

	@RequestMapping("/session")
	public String test(HttpServletRequest req,ModelMap model){
		HttpSession session=req.getSession();
		model.addAttribute("session",session.getId());
		return "session";
	}
}
