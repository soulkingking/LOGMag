package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.SeatOrderStatusDaoImpl;
import com.chdw.loc.domain.SeatOrderStatus;

/**
 * Servlet implementation class SeatOrderStatusPaging
 */
@WebServlet("/SeatOrderStatusPaging.view")
public class SeatOrderStatusPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatOrderStatusPaging() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndex = request.getParameter("pageIndex");
		// 得到查询条件进行刷选查询
		String where = request.getParameter("pageIndex");
		if(where == null || where.equals("")){
			where = "";
		}
		
		int currPage;
		if(pageIndex==null){
			currPage = 1;
			request.getSession().setAttribute("queryCondition", where); // 将最新的查询条件放入session
		}
		else{
			// 翻页时使用
			currPage = Integer.parseInt(pageIndex);
			where = request.getSession().getAttribute("queryCondition").toString();
		}
		
		SeatOrderStatusDaoImpl seatOrderStatusDaoImpl = new SeatOrderStatusDaoImpl();
		PagingBean<SeatOrderStatus> pagingBean = seatOrderStatusDaoImpl.getPageBean(5, currPage, where);
		
		request.setAttribute("seatOrderStatusPagingBean", pagingBean);
		request.getRequestDispatcher("/seatOrderStatus_mag.jsp").forward(request, response);
	}

}
