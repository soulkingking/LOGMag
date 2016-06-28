package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerCollectionDaoImpl;
import com.chdw.loc.domain.SellerCollection;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/SellerCollectionDBop")
public class SellerCollectionDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerCollectionDaoImpl sellerCollectionDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerCollectionDBop() throws Exception {
    	sellerCollectionDaoImpl =  new SellerCollectionDaoImpl();
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
		
		String opType = request.getParameter("opType").trim();
		
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		}
			
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		SellerCollection scoll = new SellerCollection();
		scoll.setS_id(s_id);
		scoll.setU_id(u_id);
		
		int result = sellerCollectionDaoImpl.add(scoll);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String scoll_id = request.getParameter("scoll_id").trim();
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		SellerCollection scoll = new SellerCollection();
		scoll.setScoll_id(scoll_id);
		scoll.setS_id(s_id);
		scoll.setU_id(u_id);
		
		int result = sellerCollectionDaoImpl.update(scoll);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] IDs = request.getParameterValues("Ids");
		String ids = "";
		if (IDs != null) {
			StringBuffer sb = new StringBuffer();
			for (String string : IDs) {
				sb.append("'").append(string).append("'").append(",");
			}
			ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
		} else {
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		}
		
		int result = sellerCollectionDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SellerCollectionPaging.view").forward(request, response);
			return ;
		}
	}
	
}
