package crm_project_02.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "demoCookieController", urlPatterns = {"/demo-cookie"})
public class DemoCookieController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Mục dích: Lấy giá trị đã lưu trữ trong cookies 
		Cookie[] arrayCookies = req.getCookies();
		
		for (Cookie cookie : arrayCookies) {
			if(cookie.getName().equals("hoten")) {
				String value = cookie.getValue();
				System.out.println("Kiem tra " + URLDecoder.decode(value, "UTF-8"));
			}
		}
	}
	
}
