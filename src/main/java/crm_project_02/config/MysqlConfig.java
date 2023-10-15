package crm_project_02.config;

import java.sql.Connection;
import java.sql.DriverManager;

//Cấu hình JDBC kết nối tới server MYSQL
public class MysqlConfig {
	
	public static Connection getConnection() {
		
		try {
			
			// Khai báo driver sử dụng JDBC tương ứng với cơ sở dữ liệu cần kết nối
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Khai báo Driver sẽ mở kết nối tới CSDL nào 
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm", "root", "admin123");
			
		} catch (Exception e) {
			// Lỗi xảy ra sẽ chạy vào đây
			System.out.println("Lỗi kết nối database " + e.getLocalizedMessage());
			return null;
		}
		
	}

}
