package crm_project_02.controller;

import java.io.IOException;

import crm_project_02.service.StatusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "statusController", urlPatterns = {"/status-add"})
public class StatusController extends HttpServlet{
	
	private StatusService statusService = new StatusService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		req.getRequestDispatcher("/status-add.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		boolean isSuccess = statusService.addStatus(name);
		
		req.setAttribute("isSuccess", isSuccess);
		
		req.getRequestDispatcher("status-add.jsp").forward(req, resp);
	}
}