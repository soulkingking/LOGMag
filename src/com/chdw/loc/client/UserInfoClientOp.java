package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.Md5Encoder;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/UserInfoClientOp")
public class UserInfoClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserInfoDaoImpl userInfoDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoClientOp() throws Exception {
    	userInfoDaoImpl = new UserInfoDaoImpl();
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
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		
		String opType = request.getParameter("opType").trim();
		if (opType == null || opType.equals("")) {
			opType = "";
		}
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		} else if (opType.equals("select")) {
			select(request, response, where);
		}
		return;
		
	}
	
	private void select(HttpServletRequest request,
			HttpServletResponse response, String where) throws ServletException, IOException {
		// 生成返回对象的集合
		List<UserInfo> sellerList = userInfoDaoImpl.findAll(where);
		//返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
	}

	//拼凑json字符串
	public String createJsonStr(List<UserInfo> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (UserInfo ui : list) {
			sb.append('{');
			sb.append("u_id:\"").append(ui.getU_id()).append("\",");
			sb.append("user_icon:\"").append(ui.getUser_icon()).append("\",");
			sb.append("user_alias:\"").append(ui.getUser_alias()).append("\",");
			sb.append("user_sex:\"").append(ui.getUser_sex()).append("\",");
			sb.append("user_age:\"").append(ui.getUser_age()).append("\",");
			sb.append("user_signature:\"").append(ui.getUser_signature()).append("\",");
			sb.append("username:\"").append(ui.getUsername()).append("\",");
			sb.append("pword:\"").append(ui.getPassword()).append("\",");
			sb.append("token:\"").append(ui.getToken()).append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
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
		ui.setUser_icon("/upload/UserIcon/default.png");
		ui.setUsername(username);
		ui.setPassword(Md5Encoder.encode(password));
		ui.setUser_alias(alias);
		ui.setUser_sex(sex);
		ui.setUser_age(Integer.parseInt(age));
		ui.setUser_signature(signature);
		
		int result = userInfoDaoImpl.add(ui);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
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
		ui.setUsername(username);
		ui.setPassword(password);
		ui.setUser_alias(alias);
		ui.setUser_sex(sex);
		ui.setUser_age(Integer.parseInt(age));
		ui.setUser_signature(signature);
		ui.setU_id(userId);
		
		int result = userInfoDaoImpl.update(ui);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
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
			out.print("");
			return ;
		}
		
		int result = userInfoDaoImpl.delete(ids);
		
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

}
