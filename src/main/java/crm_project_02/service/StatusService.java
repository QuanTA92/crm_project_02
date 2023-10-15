package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Status;
import crm_project_02.repository.StatusRepository;

public class StatusService {
	
	private StatusRepository statusRepository = new StatusRepository();
	
	public boolean addStatus(String name) {
		
		int count = statusRepository.insert(name);
		
		return count > 0;
	}
	
	public List<Status> getAllStatus(){
		
		return statusRepository.getAllStatus();
	}
}