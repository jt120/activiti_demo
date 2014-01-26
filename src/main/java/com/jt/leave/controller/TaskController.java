/**
 * @author liuze
 *
 * Jan 26, 2014
 */
package com.jt.leave.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.leave.domain.User;

@Controller
@RequestMapping("/task")
public class TaskController {

	private TaskService taskService;
	private FormService formService;
	private IdentityService identityService;
	
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
		User user = (User) request.getSession().getAttribute("loginUser");
		identityService.setAuthenticatedUserId(user.getId());
		
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
	
	
}
