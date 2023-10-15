package crm_project_02.service;

import java.sql.Date;
import java.util.List;

import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Role;
import crm_project_02.entity.Status;
import crm_project_02.entity.Users;
import crm_project_02.repository.JobRepository;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.repository.StatusRepository;
import crm_project_02.repository.UserRepository;

public class JobService {
	
	private JobRepository jobRepository = new JobRepository();
	private UserRepository userRepository = new UserRepository();
	private ProjectRepository projectRepository = new ProjectRepository();
	private StatusRepository statusRepository = new StatusRepository();
	
	public boolean insertJob(int idProject, String name, int idUser, Date startDate, Date endDate, int idStatus) {
		int count = jobRepository.insert(idProject, name, idUser, startDate, endDate, idStatus);
		return count > 0;
	}
	
	public List<Project> getAllProject(){
		return projectRepository.getAllProject();
		
	}
	
	public List<Users> getAllUsers(){
		return userRepository.getAllUsers();
		
	}
	
	public List<Status> getAllStatus(){
		return statusRepository.getAllStatus();
		
	}
	
	public List<Job> getAllJob(){
		
		return jobRepository.getAllJob();
	}
	
	public boolean deleteJob(int id) {
		int count = jobRepository.deleteById(id);
		return count > 0;
	}
	
	public boolean updateJob(int id, int idProject, String name, int idUser, Date startDate, Date endDate, int idStatus) {
		
		int count = jobRepository.updateJob(id, idProject, name, idUser, startDate, endDate, idStatus);
		
		return count > 0;
	}
	
	
}
