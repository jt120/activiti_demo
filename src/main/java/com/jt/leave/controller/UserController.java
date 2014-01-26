/**
 * @author liuze
 *
 * Jan 26, 2014
 */
package com.jt.leave.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.leave.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user, HttpServletRequest request) {
		request.getSession().setAttribute("loginUser", user);
		return "redirect:/process/list";
	}

}
