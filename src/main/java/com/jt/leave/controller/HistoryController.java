/**
 * @author liuze
 *
 * Jan 26, 2014
 */
package com.jt.leave.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
	
	private HistoryService historyService;
	
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
		String user = (String) session.getAttribute("loginUser");
		List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
				.taskAssignee(user)
				.finished()
				.list();
		model.addAttribute("historicTaskInstances", historicTaskInstances);
		return "/history/list";
	}
	
	@RequestMapping("/{id}/view")
	public String view(@PathVariable("id") String processInstanceId, Model model) {
		List<HistoricTaskInstance> historicTaskInstances = 
				historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).list();
		List<HistoricVariableInstance> historicVariableInstances = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstanceId).list();
		model.addAttribute("historicTaskInstances", historicTaskInstances);
		model.addAttribute("historicVariableInstances", historicVariableInstances);
		return "/history/view";
	}

	public HistoryService getHistoryService() {
		return historyService;
	}
	@Resource
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
}
