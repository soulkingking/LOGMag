package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.SeatInfoDaoImpl;
import com.chdw.loc.domain.SeatInfo;

@WebServlet("/SeatInfoPaging.view")
public class SeatInfoPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SeatInfoPaging() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
		String where = request.getParameter("condition");
		if(where == null || where.equals("")){
			where = "";
		}
		
		int currPage;
		if(pageIndex==null){
			//这语句块是第一次点击查询时会调用
			currPage = 1;
			request.getSession().setAttribute("queryCondition", where);// 将最新的查询条件放入session
		}
		else{
			// 翻页时使用
			currPage = Integer.parseInt(pageIndex);
			where = request.getSession().getAttribute("queryCondition").toString();
		}
		
		SeatInfoDaoImpl seatInfoDaoImpl = new SeatInfoDaoImpl();
		PagingBean<SeatInfo> pagingBean = seatInfoDaoImpl.getPageBean(5, currPage, where);
		
		request.setAttribute("seatInfoPagingBean", pagingBean);
		request.getRequestDispatcher("/seatInfo_mag.jsp").forward(request, response);
	}

}
