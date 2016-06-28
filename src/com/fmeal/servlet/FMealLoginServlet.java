package com.fmeal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.GetAndroidJson;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.UserDao;
import com.fmeal.daoimpl.UserDaoImpl;

/**
 * Servlet implementation class FMealLoginServlet
 */
@WebServlet("/FMealLoginServlet")
public class FMealLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		UserInfo userInfo=(UserInfo) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request), UserInfo.class);
		UserDao userDao=new UserDaoImpl();
		UserInfo user=userDao.login(userInfo.getUsername(), userInfo.getPassword());
		response.getWriter().print(GsonUtil.toJson(user));
	}

}
