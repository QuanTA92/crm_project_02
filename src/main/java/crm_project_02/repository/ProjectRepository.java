package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;
import org.eclipse.tags.shaded.org.apache.regexp.recompile;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Project;

public class ProjectRepository {
	
	public int insert(String name, Date startDate, Date endDate) {

	int count = 0;
	
	String query = "INSERT INTO Project(name, startDate, endDate) VALUES (?, ?, ?)";
	
	Connection connection = MysqlConfig.getConnection();
	
	try {
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, name);
		statement.setDate(2, startDate);
        statement.setDate(3, endDate);
        
        count = statement.executeUpdate();
        
	} catch (SQLException e) {
		
		System.out.println("Loi them Project " + e.getLocalizedMessage());
		
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Loi dong ket noi " + e.getLocalizedMessage());
		}
	}
	return count;
	
	}
	
	public List<Project> getAllProject(){
	
	List<Project> listProject = new ArrayList<Project>();
	
	String query = "SELECT * FROM Project";
	
	Connection connection = MysqlConfig.getConnection();
	
	
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			Project project = new Project();
			
			project.setId(resultSet.getInt("id"));
			project.setName(resultSet.getString("name"));
			project.setStartDate(resultSet.getDate("startDate"));
			project.setEndDate(resultSet.getDate("endDate"));
			
			listProject.add(project);
			
		}
	} catch (SQLException e) {
		System.out.println("Loi lay danh sach project " + e.getLocalizedMessage());
	}
	
	finally {
		try {
			connection.close();
		} catch (SQLException e) {
			
			System.out.println("Loi dong ket noi " + e.getLocalizedMessage());
		}
	}
	
	return listProject;
	}
	
	public int deleteById(int id) {
		
		int count = 0;
		
		String query = "DELETE FROM Project p WHERE p.id = ?";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Loi xoa groupwork " + e.getLocalizedMessage());
		}
		
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Loi dong ket noi " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public int updateProject(String name, Date startDate, Date endDate, int id) {
		int count = 0;
		
		String query = "UPDATE Project SET name = ?, startDate = ?, endDate = ? WHERE id = ?;";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, endDate);
			statement.setDate(3, endDate);
			statement.setInt(4, id);
			
			count = statement.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Loi update project: " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Loi dong ket noi update user: " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
}
