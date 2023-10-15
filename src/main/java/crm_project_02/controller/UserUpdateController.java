package crm_project_02.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.service.RoleService;
import crm_project_02.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userUpdateController", urlPatterns = {"/user-update"})
public class UserUpdateController extends HttpServlet{
	
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user-update":
		
		List<Role> listRoleUpdateUser = roleService.getAllRole();
		
		req.setAttribute("listRole", listRoleUpdateUser);
		req.getRequestDispatcher("user-update.jsp").forward(req, resp);
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
		int idRole = Integer.parseInt(req.getParameter("role"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		if (firstname.isEmpty() || lastname.isEmpty() || fullname.isEmpty()
				|| username.isEmpty() || password.isEmpty() 
				|| phone.isEmpty() || email.isEmpty()) {
			
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("user-update.jsp").forward(req, resp);
            return;
        }
		boolean isSuccess = userService.updateUser(firstname, lastname, fullname, username, password, phone, email, idRole, id);
		
		List<Role> listRoleUpdateUser = new ArrayList<Role>();
		listRoleUpdateUser = userService.getAllRole();
		
		req.setAttribute("listRole", listRoleUpdateUser);
		req.setAttribute("isSuccess", isSuccess);
		
		req.getRequestDispatcher("user-update.jsp").forward(req, resp);
	}
}