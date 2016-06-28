package com.chdw.loc.paging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.impl.SellerDishDaoImpl;
import com.chdw.loc.domain.SellerDish;

/**
 * Servlet implementation class SellerDishPaging
 */
@WebServlet("/SellerDishPaging.view")
public class SellerDishPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerDishPaging() throws Exception {
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
		//String smtIdStr = request.getParameter("smtFk");	//获取菜肴对应的类别外键
		//得到查询条件进行筛选查询
		String where="";
		if (request.getSession().getAttribute("identityId")!=null) {
			String s_id=(String) request.getSession().getAttribute("identityId");
			where = "Where s_id="+"\'"+s_id+"\'" ;
		}else {
			where = request.getParameter("condition");
			if (where == null || where.equals("")) {
				where = "";
			}
		}
//		String where = request.getParameter("condition");
//		if (where == null || where.equals("")) {
//			where = "";
//		}
		
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
		
//		PagingBean<SellerMenuType> sellerMenuTypePagingBean = (PagingBean<SellerMenuType>) request.getAttribute("sellerMenuTypePagingBean");
//		for (SellerMenuType sellerMenuType : sellerMenuTypePagingBean.getList()) {
//			PagingBean<SellerDish> pagingBean = sellerDishDaoImpl.getPageBean(3, currPage, " where smt_id= " + sellerMenuType.getSmt_id() + where);
//			allDishesForSeller.put(sellerMenuType.getSmt_id(), pagingBean);
//		}
		
		SellerDishDaoImpl sellerDishDaoImpl = new SellerDishDaoImpl();
		PagingBean<SellerDish> pagingBean = sellerDishDaoImpl.getPageBean(9, currPage,  where);
		
		request.setAttribute("sellerDishPagingBean", pagingBean);
		request.getRequestDispatcher("/sellerDish_mag.jsp").forward(request,response);
		return ;
	}

}
