package com.chdw.loc.DBop;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.Md5Encoder;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/UserInfoDBop")
@MultipartConfig()
public class UserInfoDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserInfoDaoImpl userInfoDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoDBop() throws Exception {
		userInfoDaoImpl =  new UserInfoDaoImpl();
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


		String opType = request.getParameter("opType").trim();

		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		}


	}

	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String alias = request.getParameter("alias").trim();
		String sex = request.getParameter("sex").trim();
		String age = request.getParameter("age").trim();
		String signature = request.getParameter("signature").trim();

		UserInfo ui = new UserInfo();
		ui.setU_id(StringHandler.createSerialId(System.currentTimeMillis()));
		ui.setUser_icon("/upload/UserIcon/default.png");
		ui.setUsername(username);
		ui.setPassword(password);
		ui.setUser_alias(alias);
		ui.setUser_sex(sex);
		ui.setUser_age(Integer.parseInt(age));
		ui.setUser_signature(signature);

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

	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SmartUploadException {

		String userId= request.getParameter("userId").trim();
		String userIcon = request.getParameter("userIcon").trim();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String alias = request.getParameter("alias").trim();
		String sex = request.getParameter("sex").trim();
		String age = request.getParameter("age").trim();
		String signature = request.getParameter("signature").trim();

		UserInfo ui = new UserInfo();
		ui.setUser_icon(userIcon);
		//		String ext=smart.getFiles().getFile(0).getFileExt().trim();//此为得到文件的扩展名,getFile(0)为得到唯一的一个上传文件
		//		if ("".equals(ext) || ext == null) {
		//			ui.setUser_icon(userIcon);
		//		} else {
		//			String path = "/upload/UserIcon/"+ userId + "." + ext;
		//			//保存上传文件
		//			smart.getFiles().getFile(0).saveAs("D:/Pro/JavaWeb/LOGMag/WebContent/upload/UserIcon" + java.io.File.separator + userId + "." + ext) ;
		//			ui.setUser_icon(path);
		//		}
		ui.setUsername(username);
		ui.setPassword(password);
		ui.setUser_alias(alias);
		ui.setUser_sex(sex);
		ui.setUser_age(Integer.parseInt(age));
		ui.setUser_signature(signature);
		ui.setU_id(userId);

		int result = userInfoDaoImpl.update(ui);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		}
	}

	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] IDs = request.getParameterValues("Ids");
		String ids = "";
		if (IDs != null) {
			StringBuffer sb = new StringBuffer();
			for (String string : IDs) {
				sb.append("'").append(string).append("'").append(",");
			}
			ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
		} else {
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		}

		int result = userInfoDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/UserInfoPaging.view").forward(request, response);
			return ;
		}
	}

	//	public String getParameter(HttpServletRequest request, String name, String charset) throws IOException, ServletException {
	//		String value = request.getParameter(name);
	//		if(value != null){
	//			return value;
	//		}
	//		
	//		Part part = request.getPart(name);
	//		try{
	//			Reader in = new InputStreamReader(part.getInputStream(), charset);
	//			StringBuilder sb = new StringBuilder();
	//			char[] buffer = new char[256];
	//			int len=0;
	//			while ((len = in.read(buffer, 0, len)) != -1) {
	//				sb.append(buffer, 0, len);
	//			}
	//			in.close();
	//			return sb.toString();
	//		} catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		return value;
	//	}

}
