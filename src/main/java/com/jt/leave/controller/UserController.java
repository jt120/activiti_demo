/**
 * @author liuze
 *
 * Jan 26, 2014
 */
package com.jt.leave.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private IdentityService identityService;
	private HistoryService historyService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List users = identityService.createUserQuery().list();
		model.addAttribute("users", users);
		return "/user/list";
	}
	
	@RequestMapping("/{id}/view")
	public String view(@PathVariable("id") String userId, Model model) {
		UserEntity user = (UserEntity) identityService.createUserQuery().userId(userId).singleResult();
		model.addAttribute("user", user);
		return "/user/view";
	}

	public IdentityService getIdentityService() {
		return identityService;
	}
	@Resource
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}
	@Resource
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	
	
	

}
