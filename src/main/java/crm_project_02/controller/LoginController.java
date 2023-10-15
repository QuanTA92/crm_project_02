package crm_project_02.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Users;
import crm_project_02.service.JobService;
import crm_project_02.service.LoginService;
import crm_project_02.service.ProjectService;
import crm_project_02.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet (name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private LoginService loginService = new LoginService();
	private JobService jobService = new JobService();
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int maxAge = 8 * 60 * 60;
		Cookie cookie = new Cookie("hoten", URLEncoder.encode("Nguyễn Văn A", "UTF-8"));
		cookie.setMaxAge(maxAge);
		
		resp.addCookie(cookie);
		req.getRequestDispatcher("login.html").forward(req, resp);
		
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String query = "SELECT u.id, u.email, u.pwd, u.id_role, r.name as role_name FROM Users u " +
                       "INNER JOIN Role r ON u.id_role = r.id " +
                       "WHERE u.email = ? AND u.pwd = ?";
        
        Connection connection = MysqlConfig.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	
                int userId = resultSet.getInt("id");
                int roleId = resultSet.getInt("id_role");
                String roleName = resultSet.getString("role_name");

                HttpSession session = req.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("email", resultSet.getString("email"));
                session.setAttribute("userRole", roleName);

                if ("ADMIN".equals(roleName)) {
                	
                	req.getRequestDispatcher("index.jsp").forward(req, resp);
                    return;
                    
                } else if ("LEADER".equals(roleName)) {
                	req.getRequestDispatcher("index.jsp").forward(req, resp);
                    return;
                    
                } else if ("MEMBER".equals(roleName)) {
                	
                	List<Job> listJob = jobService.getAllJob();
        			
        			
        			req.setAttribute("listJob", listJob);
        			req.getRequestDispatcher("job-table-member.jsp").forward(req, resp);
        			return;
                }
            } else {
                req.getRequestDispatcher("login.html").forward(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi truy vấn " + e.getLocalizedMessage());
        }
    }
}
