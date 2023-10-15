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
import crm_project_02.service.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name ="roleController", urlPatterns = {"/role-add", "/role"})
public class RoleController extends HttpServlet{

	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		
		if (path.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			
		} else if (path.equals("/role")) {
			
			List<Role> listRoles = roleService.getAllRole();

			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String roleName = req.getParameter("role-name");
		String desc = req.getParameter("desc");
		
		if (roleName.isEmpty() || desc.isEmpty()) {
			
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("role-add.jsp").forward(req, resp);
            return;
        }
		
		boolean isSuccess = roleService.addRole(roleName, desc);
		
		req.setAttribute("isSuccess", isSuccess);
		
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
}
