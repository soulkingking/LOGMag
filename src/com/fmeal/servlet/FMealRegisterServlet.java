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
import com.chdw.loc.util.StringHandler;
import com.fmeal.dao.UserDao;
import com.fmeal.daoimpl.UserDaoImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class FMealRegisterServlet
 */
@WebServlet("/FMealRegisterServlet")
public class FMealRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		UserInfo userInfo=(UserInfo) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request), UserInfo.class);
		userInfo.setU_id(StringHandler.createSerialId(System.currentTimeMillis()));
		userInfo.setUser_icon("/upload/UserIcon/default.png");
		userInfo.setToken(GsonUtil.parseJson(userInfo.getU_id(), userInfo.getUser_alias()));
		UserDao userDao=new UserDaoImpl();
		response.getWriter().print(GsonUtil.toJson(userDao.addUser(userInfo)));
	}

}
