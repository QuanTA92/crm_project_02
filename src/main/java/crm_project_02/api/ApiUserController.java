package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import crm_project_02.entity.Role;
import crm_project_02.entity.Users;
import crm_project_02.payload.reponse.BaseReponse;
import crm_project_02.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "apiUserController", urlPatterns = {"/api/user", "/api/user/delete", })
public class ApiUserController extends HttpServlet{
	
	private UserService userService = new UserService();
	
	// Khởi tạo thư viện GSON để sử dung
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		// Kiểm tra người dùng đang gọi đường dẫn nào
		if(path.equals("/api/user")) {
			List<Users> listUser = userService.getAllUsers(); 
			
			BaseReponse reponse = new BaseReponse();
			
			reponse.setStatusCode(200);
			reponse.setMessage("");
			reponse.setData(listUser);
			
			//Chuyển List hoặc mảng về JSON
			String dataJson = gson.toJson(reponse);
			
			// Trả dữ liệu dạng GSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			out.flush();
			
		} else if (path.equals("/api/user/delete")) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = userService.deleteUser(id);
			
			BaseReponse reponse = new BaseReponse();
			reponse.setStatusCode(200);
			reponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			reponse.setData(isSuccess);
			
			String json = gson.toJson(reponse);
			
			// Trả dữ liệu dạng GSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");			
			out.print(json);
		}
		
	}

	
	
}
