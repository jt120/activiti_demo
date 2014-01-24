/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.controller;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.leave.service.ProcessService;

@Controller
@RequestMapping("/process")
public class ProcessController {
	
	
	private ProcessService processService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<ProcessDefinition> processDefinitions = processService.listProcessDefinition();
		model.addAttribute("processDefinitions", processDefinitions);
		return "process/list";
	}

	public ProcessService getProcessService() {
		return processService;
	}
	@Resource
	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}
	
	

}
