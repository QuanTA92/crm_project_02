package crm_project_02.controller;

import java.io.IOException;

import crm_project_02.service.RoleService;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "roleUpdateController", urlPatterns = {"/role-update"})
public class RoleUpdateController extends HttpServlet{
	
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		
		switch (path) {
		case "/role-update": {
			
			req.getRequestDispatcher("role-update.jsp").forward(req, resp);
			break;
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleName = req.getParameter("role-name");
		String desc = req.getParameter("desc");
		int id = Integer.parseInt(req.getParameter("id"));
		
		if (roleName.isEmpty() || desc.isEmpty()) {
			
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("role-update.jsp").forward(req, resp);
            return;
        }
		
		boolean isSuccess = roleService.updateRole(roleName, desc, id);
		
		req.setAttribute("isSuccess", isSuccess);
		
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
	}
}