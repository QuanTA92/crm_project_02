package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Project;
import jakarta.servlet.http.HttpSession;

public class LoginRepository {
	
	public int loginSystem(String email, String password) {
		int count = 0;
		
		String query = "SELECT u.id, u.email, u.pwd, u.id_role, r.name as role_name FROM Users u " +
                "INNER JOIN Role r ON u.id_role = r.id " +
                "WHERE u.email = ? AND u.pwd = ?";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	
                int userId = resultSet.getInt("id");
                int roleId = resultSet.getInt("id_role");
                String roleName = resultSet.getString("role_name");
                
            } 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi dang nhap " + e.getLocalizedMessage());
		}
		return count;
	}
}
