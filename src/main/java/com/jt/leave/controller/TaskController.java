/**
 * @author liuze
 *
 * Jan 26, 2014
 */
package com.jt.leave.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.leave.utils.TaskDiagramCmd;

@Controller
@RequestMapping("/task")
public class TaskController {

	private RepositoryService repositoryService;
	private TaskService taskService;
	private FormService formService;
	private IdentityService identityService;
	private HistoryService historyService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Task> tasks = taskService.createTaskQuery().list();
		model.addAttribute("tasks", tasks);
		return "/task/list";
	}
	@RequestMapping("/{id}/complete")
	public String complete(@PathVariable("id") String taskId, Model model) {
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		model.addAttribute("taskFormData", taskFormData);
		return "/task/complete";
	}
	
	@RequestMapping(value="/{id}/complete",method=RequestMethod.POST)
	public String complete(@PathVariable("id") String taskId, Model model, HttpServletRequest request) {
		String user = (String) request.getSession().getAttribute("loginUser");
		identityService.setAuthenticatedUserId(user);
		
		Map<String,String> params = new HashMap<String,String>();
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		List<FormProperty> ps = taskFormData.getFormProperties();
		for(FormProperty p:ps) {
			if (p.isWritable()) {
				String value = request.getParameter(p.getId());
				params.put(p.getId(), value);
			}
		}
		formService.submitTaskFormData(taskId, params);
		return "redirect:/task/list";
	}
	
	@RequestMapping("/list/completed")
	public String listCompleted(Model model, HttpServletRequest request) {
		String user = (String) request.getSession().getAttribute("loginUser");
		List<HistoricProcessInstance> historicProcessInstances = 
				historyService.createHistoricProcessInstanceQuery().startedBy(user).list();
		model.addAttribute("historicProcessInstances", historicProcessInstances);
		return "/task/listHistory";
	}
	
	@RequestMapping("/list/invovlved")
	public String listInvovlved(Model model, HttpServletRequest request) {
		String user = (String) request.getSession().getAttribute("loginUser");
		List<HistoricProcessInstance> historicProcessInstances = 
				historyService.createHistoricProcessInstanceQuery().involvedUser(user).list();
		model.addAttribute("historicProcessInstances", historicProcessInstances);
		return "/task/listHistory";
	}
	@RequestMapping("/{id}/showImage")
	public void showImage(@PathVariable("id") String taskId, HttpServletResponse response) throws IOException {
		TaskDiagramCmd taskDiagramCmd = new TaskDiagramCmd(taskId);
		InputStream is = ((ServiceImpl)taskService).getCommandExecutor().execute(taskDiagramCmd);
		response.setContentType("image/png");
		IOUtils.copy(is, response.getOutputStream());
	}

	public TaskService getTaskService() {
		return taskService;
	}
	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public FormService getFormService() {
		return formService;
	}
	@Resource
	public void setFormService(FormService formService) {
		this.formService = formService;
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
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
}
