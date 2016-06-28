package com.fmeal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.SellerComment;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealCommentDao;
import com.fmeal.daoimpl.FMealCommentDaoImpl;

/**
 * Servlet implementation class FMealCommentServlet
 */
@WebServlet("/FMealCommentServlet")
public class FMealCommentServlet extends HttpServlet {
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
		String s_id=request.getParameter("s_id");
		FMealCommentDao fMealCommentDao=new FMealCommentDaoImpl();
		List<SellerComment> sellerComments=fMealCommentDao.findComment(s_id);
		response.getWriter().print(GsonUtil.toJson(sellerComments));

	}

}
