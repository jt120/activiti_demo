/**
 * @author liuze
 *
 * Jan 24, 2014
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
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.leave.domain.User;
import com.jt.leave.utils.HistoryProcessInstanceDiagramCmd;
import com.jt.leave.utils.ProcessDefinitionDiagramCmd;

@Controller
@RequestMapping("/process")
public class ProcessController {
	
	private RepositoryService repositoryService;
	private IdentityService identityService;
	private FormService formService;
	private TaskService taskService;
	private RuntimeService runtimeService;
	
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
		model.addAttribute("processDefinitions", processDefinitions);
		return "process/list";
	}
	
	@RequestMapping(value="/{id}/image")
	public void showImage( @PathVariable String id, HttpServletResponse response) throws IOException {
		Command<InputStream> cmd = null;
		cmd = new ProcessDefinitionDiagramCmd(id);
		InputStream is = ((ServiceImpl) repositoryService).getCommandExecutor().execute(cmd);
		response.setContentType("image/png");
		FileCopyUtils.copy(is, response.getOutputStream());
	}
	@RequestMapping(value="/{id}/instanceImage")
	public void showInstanceImage(@PathVariable("id") String processInstanceId, HttpServletResponse response) throws Exception {
		Command<InputStream> cmd = null;
		cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
		InputStream is = ((ServiceImpl) repositoryService).getCommandExecutor().execute(cmd);
		response.setContentType("image/png");
		int len = 0;
		byte[] b = new byte[1024];

		while ((len = is.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	@RequestMapping(value="/listDeploy")
	public String listDeploy(Model model) {
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		model.addAttribute("deployments", deployments);
		return "process/listDeploy";
	}
	
	@RequestMapping(value="/{id}/prepare")
	public String prepareStartDeployment(@PathVariable("id") String processDefinitionId, Model model) {
		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		model.addAttribute("startFormData", startFormData);
		return "process/prepare";
	}
	
	@RequestMapping(value="/{id}/start",method=RequestMethod.POST)
	public String startDeployment(@PathVariable("id") String processDefinitionId, Model model, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null) {
			return "user/login";
		}
		identityService.setAuthenticatedUserId(loginUser.getId());
		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		Map<String,String> params = new HashMap<String,String>();
		for(FormProperty formProperty:startFormData.getFormProperties()) {
			
			String var = request.getParameter(formProperty.getId());
			params.put(formProperty.getId(), var);
		}
		formService.submitStartFormData(processDefinitionId, params);
		return "redirect:/task/list";
	}
	

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	@Resource
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}
	@Resource
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public FormService getFormService() {
		return formService;
	}
	@Resource
	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public TaskService getTaskService() {
		return taskService;
	}
	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	@Resource
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	
	

}
