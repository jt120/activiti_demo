/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.activiti;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestSample {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private FormService formService;
	@Autowired
	private ManagementService managementService;
	
	@Autowired
	ApplicationContext ctx;
	
	
	@Test
	public void testQueryProcessDefinition() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		
		for(ProcessDefinition def:list) {
			System.out.println(def.getId());
		}
	}
	@Test
	public void testDeploy() {
		List<Deployment> list = repositoryService.createDeploymentQuery().list();
		for(Deployment d:list) {
			System.out.println("deployment id: "+d.getId());
		}
	}
	
	@Test
	public void testDeleteDeployment() {
		List<Deployment> list = repositoryService.createDeploymentQuery().list();
		for(Deployment d:list) {
			repositoryService.deleteDeployment(d.getId());
		}
	}
	@Test
	public void testActiveProcessDefinition() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		
		for(ProcessDefinition def:list) {
			String id = def.getId();
			if(id.equals("hello:1:30")) {
				//already in active
				repositoryService.activateProcessDefinitionById(id);
			}
		}
	}

	@Test
	public void testQueryProcessInstance() {
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
		for(ProcessInstance instance:list) {
			System.out.println("instance id: "+instance.getId());
		}
	}
	
	@Test
	public void testStartInstance() {
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("hello");
		assertNotNull(instance);
		
	}
	
	@Test
	public void testFinishTask() {
		List<Task> tasks = taskService.createTaskQuery().list();
		for(Task p:tasks) {
			System.out.println("complete id: "+p.getId());
			taskService.complete(p.getId());
			
		}
	}
	
	

}
