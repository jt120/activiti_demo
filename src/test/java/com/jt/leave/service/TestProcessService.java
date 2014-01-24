/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestProcessService {
	
	@Autowired
	private ProcessService processService;

	@Test
	public void testExisted() {
		assertNotNull(processService);
		
		processService.listProcessDefinition();
	}
}
