package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.UserInfoDaoImpl;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.StringHandler;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		String alias = request.getParameter("alias").trim();
		String sex = request.getParameter("sex").trim();
		String username = request.getParameter("uname").trim();
		String password = request.getParameter("pword").trim();
		
		UserInfo ui = new UserInfo();
		String id=StringHandler.createSerialId(System.currentTimeMillis());
		ui.setU_id(id);
		ui.setUser_icon("/upload/UserIcon/default.png");
		ui.setUser_alias(alias);
		ui.setUser_sex(sex);
		ui.setUser_age(16);
		ui.setUser_signature("这个人很懒，什么也没留下!");
		ui.setUsername(username);
		ui.setPassword(password);
		
		UserInfoDaoImpl userValidDaoImpl = new UserInfoDaoImpl();
		int result = userValidDaoImpl.add(ui);
		if (result == 0) {
			out.print("ACCOUNT_NOT_REGISTER");
		} else if (result == 1) {
			//注册成功
			//out.print("ACCOUNT_REGISTER_SUCCESS");
//			String ap_curpoint = request.getParameter("ap_curpoint").trim();
//			String ap_maxpoint = request.getParameter("ap_maxpoint").trim();
//			String ap_maxWeekRank = request.getParameter("ap_maxWeekRank").trim();
//			String ap_maxMonthRank = request.getParameter("ap_maxMonthRank").trim();
//			String ap_maxRank = request.getParameter("ap_maxRank").trim();
//			String ap_curWeekDif = request.getParameter("ap_curWeekDif").trim();
//			String ap_curMonthDif = request.getParameter("ap_curMonthDif").trim();
//			String u_id = request.getParameter("u_id").trim();
			request.setAttribute("ap_curpoint", 0);
			request.setAttribute("ap_maxpoint", 0);
			request.setAttribute("ap_maxWeekRank", userValidDaoImpl.getTotalCount("") + 1);
			request.setAttribute("ap_maxMonthRank", userValidDaoImpl.getTotalCount("") + 1);
			request.setAttribute("ap_maxRank", userValidDaoImpl.getTotalCount("") + 1);
			request.setAttribute("ap_curWeekDif", 0);
			request.setAttribute("ap_curMonthDif", 0);
			request.setAttribute("u_id", id);
			request.getRequestDispatcher("/AccountPointClientOp?opType=add").forward(request,response);
			return;
		} else if (result == 2) {
			//账号已被注册
			out.print("ACCOUNT_ALREADY_REGISTER");
		}
	}

}
