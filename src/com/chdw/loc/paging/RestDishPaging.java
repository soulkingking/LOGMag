package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.RestDishDaoImpl;
import com.chdw.loc.domain.RestDish;

/**
 * Servlet implementation class SellerDishPaging
 */
@WebServlet("/RestDishPaging.view")
public class RestDishPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RestDishPaging() throws Exception {
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
		
		String pageIndex = request.getParameter("pageIndex");
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		
		int currPage;
		if(pageIndex==null){
			// 这语句块是第一次登录时与点击查询时会调用
			currPage=1;
			request.getSession().setAttribute("queryCondition", where);//将最新的查询条件放入session
		} else {
			// 翻页时使用
			currPage=Integer.parseInt(pageIndex);
			where = request.getSession().getAttribute("queryCondition").toString();
		}
		
		RestDishDaoImpl restDishDaoImpl = new RestDishDaoImpl();
		PagingBean<RestDish> pagingBean = restDishDaoImpl.getPageBean(9, currPage,  where);
		
		request.setAttribute("restDishPagingBean", pagingBean);
		request.getRequestDispatcher("/restDish_mag.jsp").forward(request,response);
		return ;
	}

}
