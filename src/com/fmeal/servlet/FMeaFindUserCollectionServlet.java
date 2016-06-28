package com.fmeal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.Seller;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealCollectionDao;
import com.fmeal.daoimpl.FMealCollectionDaoImpl;

/**
 * Servlet implementation class FMeaFindUserCollectionServlet
 */
@WebServlet("/FMeaFindUserCollectionServlet")
public class FMeaFindUserCollectionServlet extends HttpServlet {
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
		String u_id=request.getParameter("u_id");
		FMealCollectionDao fMealCollectionDao=new FMealCollectionDaoImpl();
		List<Seller> sellers=fMealCollectionDao.findUserCollection(u_id);
		response.getWriter().print(GsonUtil.toJson(sellers));
	}

}
