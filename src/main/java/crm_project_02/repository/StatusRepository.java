package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Status;

public class StatusRepository {
	
	public int insert(String name) {
	int count = 0;
	String query = "INSERT INTO Status(name) VALUES (?)";
	
	Connection connection = MysqlConfig.getConnection();
	
	try {
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, name);
		
		count = statement.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println("Loi dong ket noi " + e.getLocalizedMessage());
		}
	}
	return count;
	}
	
	public List<Status> getAllStatus(){
		
		List<Status> listStatus = new ArrayList<Status>();
		
		String query = "SELECT * FROM Status";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				
				listStatus.add(status);
				
			}
		} catch (SQLException e) {
		}
		
		return listStatus;
	}
}
