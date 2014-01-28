/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	protected Logger log = Logger.getLogger(ProcessController.class);
	
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
		model.addAttribute("processDefinitions", processDefinitions);
		return "process/list";
	}

	@RequestMapping(value="/create")
	public String deploy() {
		
		return "process/create";
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String deploy(HttpServletRequest request) throws UnsupportedEncodingException {
		String xml = request.getParameter("xml");
		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		String deploymentID = repositoryService.createDeployment().addInputStream("bookordera.bpmn20.xml", is)
				.deploy().getId();
		return "redirect:/process/list";
	}
	
	@RequestMapping(value="/{id}/image")
	public void showImage( @PathVariable String id, HttpServletResponse response) throws IOException {
		Command<InputStream> cmd = null;
		cmd = new ProcessDefinitionDiagramCmd(id);
		InputStream is = ((ServiceImpl) repositoryService).getCommandExecutor().execute(cmd);
		response.setContentType("image/png");
		IOUtils.copy(is, response.getOutputStream());
	}
	@RequestMapping(value="/{id}/instanceImage")
	public void showInstanceImage(@PathVariable("id") String processInstanceId, HttpServletResponse response) throws Exception {
		Command<InputStream> cmd = null;
		cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
		InputStream is = ((ServiceImpl) repositoryService).getCommandExecutor().execute(cmd);
		response.setContentType("image/png");
		IOUtils.copy(is, response.getOutputStream());
	}
	
	@RequestMapping(value="/listDeploy")
	public String listDeploy(Model model) {
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		model.addAttribute("deployments", deployments);
		return "process/listDeploy";
	}
	
	public String startDeploy() {
		return "redirect:/listDeploy";
	}
	
	
	@RequestMapping(value="/{id}/prepareDeploy")
	public String prepareStartDeployment(@PathVariable("id") String deployId, Model model) {
		
//		runtimeService.startProcessInstanceByKey(processDefinitionKey)
		return "process/prepare";
	}
	
	@RequestMapping(value="/{id}/prepare")
	public String prepareStartDefinition(@PathVariable("id") String processDefinitionId, Model model) {
		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		model.addAttribute("startFormData", startFormData);
		return "process/prepare";
	}
	
	@RequestMapping(value="/{id}/start",method=RequestMethod.POST)
	public String startDefinition(@PathVariable("id") String processDefinitionId, Model model, HttpServletRequest request) {
		String user = (String) request.getSession().getAttribute("loginUser");
		identityService.setAuthenticatedUserId(user);
		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		Map<String,String> params = new HashMap<String,String>();
		for(FormProperty formProperty:startFormData.getFormProperties()) {
			
			String var = request.getParameter(formProperty.getId());
			params.put(formProperty.getId(), var);
		}
		formService.submitStartFormData(processDefinitionId, params);
		return "redirect:/task/list";
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:/process/list";
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
