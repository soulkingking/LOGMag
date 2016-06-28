package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.RestCommentDaoImpl;
import com.chdw.loc.domain.RestComment;

/**
 * Servlet implementation class SellerCommentControl
 */
@WebServlet("/RestCommentPaging.view")
public class RestCommentPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestCommentPaging() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRquest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRquest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processRquest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
			request.getSession().setAttribute("queryCondition", where);//将最新的查询条件放入session
		} else {
			// 翻页时使用
			currPage=Integer.parseInt(pageIndex);
			where = request.getSession().getAttribute("queryCondition").toString();
		}
		
		//需要一个DAO，从数库中获取相关信息
		RestCommentDaoImpl restCommentDaoImpl=new RestCommentDaoImpl();
		//开始构建pageBean
		PagingBean<RestComment> pageBean=restCommentDaoImpl.getPageBean(5,currPage, where);
	
		request.setAttribute("restCommentPagingBean", pageBean);
		
		request.getRequestDispatcher("/restComment_mag.jsp").forward(request,response);
		
	}
}
