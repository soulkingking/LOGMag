package com.fmeal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealOrderDao;
import com.fmeal.daoimpl.FMealOrderDaoImpl;

/**
 * Servlet implementation class FMealUpdateOrderStatusServlet
 */
@WebServlet("/FMealUpdateOrderStatusServlet")
public class FMealUpdateOrderStatusServlet extends HttpServlet {
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
		String tos_id=request.getParameter("tos_id");
		FMealOrderDao fMealOrderDao=new FMealOrderDaoImpl();
		Boolean isSuccess=fMealOrderDao.pay(tos_id);
		response.getWriter().print(GsonUtil.toJson(isSuccess));

	}

}
