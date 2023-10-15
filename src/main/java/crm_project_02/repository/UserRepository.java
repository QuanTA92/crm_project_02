package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Role;
import crm_project_02.entity.Users;

public class UserRepository {
	
	public int insert(String firtname, String lastname, String fullname, String username , String pwd, String phone, String email, int idRole) {
		int count = 0;
		String query = "INSERT INTO Users (firstName, lastName, fullName, userName, email, pwd, phone, id_role) "
	             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection connection = MysqlConfig.getConnection();	
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, firtname);
			statement.setString(2, lastname);
			statement.setString(3, fullname);
			statement.setString(4, username);
			statement.setString(5, email);
			statement.setString(6, pwd);
			statement.setString(7, phone);
			statement.setInt(8, idRole);
	
			count = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			System.out.println("Loi them user " + e.getLocalizedMessage());
		
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Loi dong ket noi " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public List<Users> getAllUsers(){
		
		
		List<Users> listUser = new ArrayList<Users>();
		
		String query ="SELECT u.id, u.firstName, u.lastName, u.userName, r.name \r\n"
				+ "FROM Users u \r\n"
				+ "JOIN Role r ON u.id_role = r.id";
	
	Connection connection = MysqlConfig.getConnection();
	
	try {
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			Users users = new Users();
			users.setId(resultSet.getInt("id"));
			users.setFirstName(resultSet.getString("firstName"));
			users.setLastName(resultSet.getString("lastName"));
			users.setUserName(resultSet.getString("userName"));
			
			Role role = new Role();
			role.setName(resultSet.getString("name"));
			
			users.setRole(role);
			
			
			listUser.add(users);
		}
		
	} catch (Exception e) {
			System.out.println("Loi lay danh sach user " + e.getLocalizedMessage());
	} finally {
		try {
			connection.close();
		} catch (Exception e2) {
			
		}
	}
	return listUser;
	
	}
	
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM Users u WHERE u.id = ?";
		
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
	
	public int updateUser(String firtname, String lastname, String fullname, String username , String pwd, String phone, String email, int idRole, int id) {
		int count = 0;
		String query = "UPDATE Users SET firstName = ?, lastName = ?, fullName = ?, \r\n"
				+ "userName= ?, email= ?, pwd = ?, phone = ?, id_role = ? WHERE id = ?";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firtname);
			statement.setString(2, lastname);
			statement.setString(3, fullname);
			statement.setString(4, username);
			statement.setString(5, email);
			statement.setString(6, pwd);
			statement.setString(7, phone);
			statement.setInt(8, idRole);
			statement.setInt(9, id);
			
			count = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			System.out.println("Loi update user: " + e.getLocalizedMessage());
		
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