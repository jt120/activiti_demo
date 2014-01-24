/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.activiti;


import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestProcessEngine {
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
	public void test01() {
		Assert.assertNotNull(ctx);
		RepositoryService rs = ctx.getBean("repositoryService",RepositoryService.class);
		Assert.assertNotNull(repositoryService);
		Assert.assertNotNull(historyService);
		Assert.assertNotNull(taskService);
		Assert.assertNotNull(runtimeService);
		Assert.assertNotNull(identityService);
		Assert.assertNotNull(formService);
		Assert.assertNotNull(managementService);
	}
	
	
	
	


}
