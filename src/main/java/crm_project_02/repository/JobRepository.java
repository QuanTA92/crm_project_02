package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.Users;

public class JobRepository {
	public int insert(int idProject, String name, int idUser, Date startDate, Date endDate, int idStatus) {
		
		int count = 0;
		
		String query = "CALL insertJob(?,?,?,?,?,?)";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idProject);
			statement.setString(2, name);
			statement.setInt(3, idUser);
			statement.setDate(4, startDate);
			statement.setDate(5, endDate);
			statement.setInt(6, idStatus);
			
			count = statement.executeUpdate();
		
			} catch (SQLException e) {
		}
		return count;
	}
	
	public List<Job> getAllJob(){
		List<Job> listJob = new ArrayList<Job>();
		
		String query = "CALL getAllJob()";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Job job = new Job();
				
				job.setId(resultSet.getInt("id_job"));
				
				job.setName(resultSet.getString("job_name"));
				
				Project project = new Project();
				project.setName(resultSet.getString("project_name"));
				job.setProject(project);
				
				Users users = new Users();
				users.setUserName(resultSet.getString("user_name"));
				job.setUsers(users);
				
				job.setStartDate(resultSet.getDate("startDate"));
				
				job.setEndDate(resultSet.getDate("endDate"));
				
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				job.setStatus(status);
				
				listJob.add(job);
				
			}
		} catch (SQLException e) {
			System.out.println("Loi lay danh sach Job: " + e.getLocalizedMessage());
		}
		
		return listJob;
	}
	
	public int deleteById(int id) {
		int count = 0;
		
		String query = "DELETE FROM Job_Status_Users jsu WHERE jsu.id_job = ?";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return count;
		
	}
	
	public int updateJob(int id, int idProject, String name, int idUser, Date startDate, Date endDate, int idStatus) {
		
		int count = 0;
		
		String query = "CALL updateJob(?,?,?,?,?,?,?)";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, idProject);
			statement.setString(3, name);
			statement.setInt(4, idUser);
			statement.setDate(5, startDate);
			statement.setDate(6, endDate);
			statement.setInt(7, idStatus);
			
			count = statement.executeUpdate();
		
			} catch (SQLException e) {
		}
		
		return count;
	}
	
}