package com.fmeal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.SellerData;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealSellerDao;
import com.fmeal.daoimpl.FMealSellerDaoImpl;

/**
 * Servlet implementation class FMealSellerDishServlet
 */
@WebServlet("/FMealSellerDishServlet")
public class FMealSellerDishServlet extends HttpServlet {
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
		String s_id= request.getParameter("s_id");
		FMealSellerDao fMealSellerDao=new FMealSellerDaoImpl();
		SellerData sellerData=fMealSellerDao.findSellerData(s_id);
		response.getWriter().print(GsonUtil.toJson(sellerData));

	}

}
