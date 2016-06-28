package com.chdw.loc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;

@WebServlet("/UploadUserIcon")
@MultipartConfig()
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
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
		
		UserInfo ui = (UserInfo) request.getAttribute("UserInfo");
		
		Part part = request.getPart("pic");
		
		String name = part.getSubmittedFileName();	//获取上传文件名
		String suffix = name.substring(name.lastIndexOf("."),name.length());
		String path = "/upload/UserIcon/"+ ui.getU_id() + suffix;
		part.write(request.getContextPath() + path);
		
		ui.setUser_icon(path);
		
		UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
		int result = userInfoDaoImpl.add(ui);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		}
	}

}
