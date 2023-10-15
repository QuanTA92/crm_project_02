package crm_project_02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.Users;
import crm_project_02.service.JobService;
import crm_project_02.service.ProjectService;
import crm_project_02.service.StatusService;
import crm_project_02.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "jobController", urlPatterns = {"/job-table", "/job-add", "/job-table-member"})
public class JobController extends HttpServlet{
	
	public ProjectService projectService = new ProjectService();
	public UserService userService = new UserService();
	public StatusService statusService = new StatusService();
	
	public JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/job-table":
			List<Job> listJob = jobService.getAllJob();
			req.setAttribute("listJob", listJob);
			req.getRequestDispatcher("job-table.jsp").forward(req, resp);
			
			
			break;
			
		case "/job-add": 
				List<Project> listProject = projectService.getAllProject();
				List<Users> listUser = userService.getAllUsers();
				List<Status> listStatus = statusService.getAllStatus();
			
			req.setAttribute("listProject", listProject);
			req.setAttribute("listUser", listUser);
			req.setAttribute("listStatus", listStatus);
			
			req.getRequestDispatcher("job-add.jsp").forward(req, resp);
			
			break;
			
			
		case "/job-table-member":
			List<Job> listJobForMember = jobService.getAllJob();
			req.setAttribute("listJob", listJobForMember);
			req.getRequestDispatcher("job-table-member.jsp").forward(req, resp);
			
			
			break;
			
			
		
		default:

		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int idProject = Integer.parseInt(req.getParameter("project"));
		String name = req.getParameter("name");
		int idUser = Integer.parseInt(req.getParameter("users"));
		String startDateStr = req.getParameter("startDate");
	    String endDateStr = req.getParameter("endDate");
	    int idStatus = Integer.parseInt(req.getParameter("status"));
	    
	    List<Project> listProject = new ArrayList<Project>();
	    List<Users> listUser = new ArrayList<Users>();
	    List<Status> listStatus = new ArrayList<Status>();
	    
	    listProject = jobService.getAllProject();
	    listUser = userService.getAllUsers();
	    listStatus = statusService.getAllStatus();
	    
	    req.setAttribute("listProject", listProject);
		req.setAttribute("listUser", listUser);
		req.setAttribute("listStatus", listStatus);
	    
	    if (name.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            req.setAttribute("emptyFields", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("job-add.jsp").forward(req, resp);
            return;
        }
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
	    
	    try {
	    	java.util.Date utilStartDate = dateFormat.parse(startDateStr);
	        java.util.Date utilEndDate = dateFormat.parse(endDateStr);
	        
	        if (utilEndDate.before(utilStartDate)) {
	            req.setAttribute("invalidEndDate", "Ngày kết thúc không hợp lệ");
	            req.getRequestDispatcher("job-add.jsp").forward(req, resp);
	            return;
	        }
	        
	        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
	        java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
	    	
	        boolean isSuccess = jobService.insertJob(idProject, name, idUser, sqlStartDate, sqlEndDate, idStatus);
		    
		    
		    
			req.setAttribute("isSuccess", isSuccess);
	        
		} catch (Exception e) {
			System.out.println("Loi add: " + e.getLocalizedMessage());
			
			
			req.setAttribute("invalidDate", "Nhập ngày không hợp lệ, vui lòng nhập đúng định dạng (yyyy/mm/dd)");
			
		}
	    
		req.getRequestDispatcher("job-add.jsp").forward(req, resp);
	
	}




}
