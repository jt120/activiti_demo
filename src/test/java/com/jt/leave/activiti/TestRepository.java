/**
 * @author liuze
 *
 * Jan 28, 2014
 */
package com.jt.leave.activiti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test-application-context.xml")
public class TestRepository {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Test
	public void deleteDeployment() {
		String deploymentID = repositoryService.createDeployment()
			.addClasspathResource("bpmn/bookorder.bpmn20.xml")
			.deploy()
			.getId();
		
		Deployment deployment = repositoryService.createDeploymentQuery().singleResult();
		assertNotNull(deployment);
		assertEquals(deploymentID, deployment.getId());
		System.out.println("Found deployment " + deployment.getId() 
		        + ", deployed at " + deployment.getDeploymentTime());

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.latestVersion().singleResult();
		assertNotNull(processDefinition);
		assertEquals("bookorder", processDefinition.getKey());
		System.out.println("Found process definition " + processDefinition.getId());

		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("isbn", "123456");
		runtimeService.startProcessInstanceByKey("bookorder", variableMap);
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().singleResult();
		assertNotNull(processInstance);
		assertEquals(processDefinition.getId(), processInstance.getProcessDefinitionId());
		
		repositoryService.deleteDeployment(deploymentID, true);
		
		deployment = repositoryService.createDeploymentQuery().singleResult();
		assertNull(deployment);
		processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
		assertNull(processDefinition);
		processInstance = runtimeService.createProcessInstanceQuery().singleResult();
		assertNull(processInstance);
	}
}
