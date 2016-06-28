package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.ParamUtils;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/UserIconUploadServlet")
public class UserIconUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public UserIconUploadServlet() throws Exception {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		
		Map<String, String> map = ParamUtils.getParams(request, "/upload/UserIcon/");
		String u_id = map.get("u_id");
		String fileName = map.get("image");
System.out.println(fileName);
		
		UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
		UserInfo ui = userInfoDaoImpl.findAll("where u_id='" + u_id + "'").get(0);
		ui.setUser_icon("/upload/UserIcon/" + fileName);
		int result = userInfoDaoImpl.update(ui);
System.out.println(result);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
