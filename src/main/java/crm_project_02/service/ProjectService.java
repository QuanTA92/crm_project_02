package crm_project_02.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;

import crm_project_02.entity.Project;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.repository.UserRepository;

public class ProjectService {

	private ProjectRepository projectRepository = new ProjectRepository();
		
	
	public List<Project> getAllProject(){
		
		return projectRepository.getAllProject();
		
	}
	
	public boolean insertProject(String name, Date startDate, Date endDate) {
		
		int count = projectRepository.insert(name, startDate, endDate);
		
		return count > 0;
	}
	
	public boolean deleteProject(int id) {
		
		int count = projectRepository.deleteById(id);
		
		return count > 0;
		
	}
	
	public boolean updateProject(String name, Date startDate, Date endDate, int id) {
		
		int count = projectRepository.updateProject(name, startDate, endDate, id);
		
		return count > 0;
	}
	
}
