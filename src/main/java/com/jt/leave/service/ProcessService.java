/**
 * @author liuze
 *
 * Jan 24, 2014
 */
package com.jt.leave.service;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

@Service("processService")
public class ProcessService {
	
	
	private RepositoryService repositoryService;
	
	public List<ProcessDefinition> listProcessDefinition() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	@Resource
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
	

}
