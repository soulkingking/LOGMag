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
 * Servlet implementation class FMealUpdateUserServlet
 */
@WebServlet("/FMealUpdateUserServlet")
public class FMealUpdateUserServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		UserInfo userInfo=(UserInfo) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request), UserInfo.class);
		UserDao userDao=new UserDaoImpl();
		boolean isSuccess=userDao.updateUser(userInfo);
		if (isSuccess) {
			UserInfo user=userDao.login(userInfo.getUsername(), userInfo.getPassword());
			response.getWriter().print(GsonUtil.toJson(user));
		}

	}

}
