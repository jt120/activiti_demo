/**
 * @author liuze
 *
 * Jan 27, 2014
 */
package com.jt.leave.dao;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jt.leave.domain.LoanRequestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "/applicationContext-test-jpa.xml" })
public class TestLoan {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	
	@Test
	public void test01() {
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("bpmn2/14-loan.bpmn20.xml").deploy();
		
		LoanRequestBean loanRequestBean = new LoanRequestBean();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("loanRequestBean", loanRequestBean);
		
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("LoanRequestProcess",variables);
		
		Object obj = runtimeService.getVariable(processInstance.getId(),"loanRequestBean");
		
		Assert.assertTrue(obj instanceof LoanRequestBean);
		
		
	}

}
