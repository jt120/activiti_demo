/**
 * @author liuze
 *
 * Jan 28, 2014
 */
package com.jt.leave.controller;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.leave.utils.UserUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		if(username!=null) {
			UserUtil.setUser(username);
			request.getSession().setAttribute("loginUser", username);
		}
		return "redirect:/process/list";
	}

}
