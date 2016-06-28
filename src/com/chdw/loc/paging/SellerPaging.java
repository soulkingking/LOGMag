package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.SellerDaoImpl;
import com.chdw.loc.domain.Seller;

/**
 * Servlet implementation class SellerPaging
 */
@WebServlet("/SellerPaging.view")
public class SellerPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerPaging() {
		super();
	}

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
		String pageIndex=request.getParameter("pageIndex");
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		int currPage;
		if(pageIndex==null){
			// 这语句块是第一次登录时与点击查询时会调用
			currPage=1;
		} else {
			// 翻页时使用
			currPage=Integer.parseInt(pageIndex);
		}
		try {
			SellerDaoImpl sellerDaoImpl = new SellerDaoImpl();
			PagingBean<Seller> pagingBean = sellerDaoImpl.getPageBean(10, currPage, "");
			request.setAttribute("sellerPagingBean", pagingBean);
			request.getRequestDispatcher("/seller_mag.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
