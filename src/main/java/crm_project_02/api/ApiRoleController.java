package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import crm_project_02.payload.reponse.BaseReponse;
import crm_project_02.service.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name ="apiRoleController", urlPatterns = ("/api/role/delete")) 
public class ApiRoleController extends HttpServlet{
	
	private RoleService roleService = new RoleService();
	
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = roleService.deleteRole(id);
		
		BaseReponse reponse = new BaseReponse();
		reponse.setStatusCode(200);
		reponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
		reponse.setData(isSuccess);
		
		String json = gson.toJson(reponse);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");			
		out.print(json);
	}
	
}
