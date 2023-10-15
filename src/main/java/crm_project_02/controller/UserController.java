package crm_project_02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Role;
import crm_project_02.entity.Users;
import crm_project_02.service.RoleService;
import crm_project_02.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userController", urlPatterns = {"/users", "/user-add"})
public class UserController extends HttpServlet{
	
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/users":
			List<Users> listUser = userService.getAllUsers();
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
			
		case "/user-add":
			List<Role> list = roleService.getAllRole();
			
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String fullname = req.getParameter("fullname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		
		if (firstname.isEmpty() || lastname.isEmpty() || fullname.isEmpty()
				|| username.isEmpty() || password.isEmpty() 
				|| phone.isEmpty() || email.isEmpty()) {
			
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
            return;
        }
		
		int idRole = Integer.parseInt(req.getParameter("role"));
		boolean isSuccess = userService.insertUser(firstname, lastname, fullname, username, password, phone, email, idRole);
		
		List<Role> list = new ArrayList<Role>();
		list = userService.getAllRole();
		
		req.setAttribute("listRole", list);
		req.setAttribute("isSuccess", isSuccess);
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
}