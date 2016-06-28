package com.fmeal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.SellerComment;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.GetAndroidJson;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealCommentDao;
import com.fmeal.daoimpl.FMealCollectionDaoImpl;
import com.fmeal.daoimpl.FMealCommentDaoImpl;

/**
 * Servlet implementation class FMealCommentServlet
 */
@WebServlet("/FMealAddCommentServlet")
public class FMealAddCommentServlet extends HttpServlet {
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
		SellerComment comment=(SellerComment) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request),SellerComment.class);
		FMealCommentDao fMealCommentDao=new FMealCommentDaoImpl();
		boolean isSuccess=fMealCommentDao.addComment(comment);
		if (isSuccess) {
			response.getWriter().print(GsonUtil.toJson(comment));
		}
	}

}
