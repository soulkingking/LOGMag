package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegetPwdServlet")
public class RegetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegetPwdServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String validCode = request.getParameter("validCode").trim();	//用户输入的验证码
		String correctCode = (String) request.getSession().getAttribute("session_LOG_ValidCode");	//服务器端生成的验证码
		if (!validCode.equalsIgnoreCase(correctCode)) {	//验证码错误
			out.println("IDENTIFY_CODE_ERROR");
		}
		
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		String password = request.getParameter("pword").trim();
		UserInfoDaoImpl userValidDaoImpl = new UserInfoDaoImpl();
		
		List<UserInfo> uis = userValidDaoImpl.findAll(where);
		if (uis == null || uis.size() == 0) {
			out.println("ACCOUNT_NOT_REGISTER");
		} else {
			UserInfo ui = uis.get(0);
			ui.setPassword(password);
			int result =  userValidDaoImpl.update(ui);
			if (result == 0) {
				out.println("CONNECT_ERROR");
			} else {
				out.println(result);
			}
		}

		
		
	}

}
