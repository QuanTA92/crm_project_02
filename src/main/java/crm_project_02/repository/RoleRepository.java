package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Role;

public class RoleRepository {

	public int insert(String name, String desc) {
		
		String query = "INSERT INTO Role(name, description) VALUES (?,?)";
	
		Connection connection = MysqlConfig.getConnection();
		
		int count = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			
			System.out.println("Loi them role " + e.getLocalizedMessage());
			
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return count;
		
	}

	public List<Role> getAllRole(){
		List<Role> listRole = new ArrayList<Role>();
		
		String query = "SELECT * FROM Role";
		
		Connection connection = MysqlConfig.getConnection();
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	

			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				listRole.add(role);
			}
		} catch (Exception e) {
		}
		return listRole;
	
	}
	
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM Role r WHERE r.id = ?";
		
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
	
	public int updateRole(String name, String desc, int id) {
		
		int count = 0;
		
		String query = "UPDATE Role SET name = ?, description = ? WHERE id = ?";
				
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			
			statement.setInt(3, id);
			
			count = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Loi update role: " + e.getLocalizedMessage());
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