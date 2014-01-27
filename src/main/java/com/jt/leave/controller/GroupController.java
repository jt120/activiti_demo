/**
 * @author liuze
 *
 * Jan 27, 2014
 */
package com.jt.leave.controller;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class GroupController {

	private IdentityService identityService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List groups = identityService.createGroupQuery().list();
		model.addAttribute("groups", groups);
		return "/group/list";
	}
	
	@RequestMapping("/{id}/view")
	public String view(@PathVariable("id") String id, Model model) {
		GroupEntity group = (GroupEntity) identityService.createGroupQuery().groupId(id).singleResult();
		model.addAttribute("group", group);
		return "/group/view";
	}

	public IdentityService getIdentityService() {
		return identityService;
	}
	@Resource
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	
	
}
