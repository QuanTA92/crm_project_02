package crm_project_02.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import crm_project_02.service.ProjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "projectUpdateController", urlPatterns = {"/project-update"})
public class ProjectUpdateController extends HttpServlet{
	
	private ProjectService projectService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/project-update": {
			req.getRequestDispatcher("project-update.jsp").forward(req, resp);
		} default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectName = req.getParameter("project-name");
		String startDateStr = req.getParameter("startDate");
	    String endDateStr = req.getParameter("endDate");
	    int id = Integer.parseInt(req.getParameter("id"));

	    if (projectName.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("project-update.jsp").forward(req, resp);
            return;
        }
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

	    try {
	    	
	        java.util.Date utilStartDate = dateFormat.parse(startDateStr);
	        java.util.Date utilEndDate = dateFormat.parse(endDateStr);
	        
	        if (utilEndDate.before(utilStartDate)) {
	            req.setAttribute("invalidEndDate", "Ngày kết thúc không hợp lệ");
	            req.getRequestDispatcher("project-update.jsp").forward(req, resp);
	            return;
	        }
	        
	        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
	        java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
	        
	        boolean isSuccess = projectService.updateProject(projectName, sqlStartDate, sqlEndDate, id);
		    
		    req.setAttribute("isSuccess", isSuccess);
		    
	    } catch (ParseException e) {
	    	req.setAttribute("invalidDate", "Nhập ngày không hợp lệ, vui lòng nhập đúng định dạng (yyyy/mm/dd)");
	        e.printStackTrace();
	    }
	    req.getRequestDispatcher("project-update.jsp").forward(req, resp);
	}
}
