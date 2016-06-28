package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerMenuTypeDaoImpl;
import com.chdw.loc.domain.SellerMenuType;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/SellerMenuTypeDBop")
public class SellerMenuTypeDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerMenuTypeDaoImpl sellerMenuTypeDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerMenuTypeDBop() throws Exception {
    	sellerMenuTypeDaoImpl =  new SellerMenuTypeDaoImpl();
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
		String smt_name = request.getParameter("smt_name").trim();
		String s_id = request.getParameter("s_id").trim();
		
		SellerMenuType smt = new SellerMenuType();
		smt.setSmt_name(smt_name);
		smt.setS_id(s_id);
		
		int result = sellerMenuTypeDaoImpl.add(smt);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String smt_id = request.getParameter("smt_id").trim();
		String smt_name = request.getParameter("smt_name").trim();
		
		SellerMenuType smt = new SellerMenuType();
		smt.setSmt_id(smt_id);
		smt.setSmt_name(smt_name);
		
		int result = sellerMenuTypeDaoImpl.update(smt);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
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
			System.out.println(ids);
		} else {
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		}
		
		int result = sellerMenuTypeDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SellerMenuTypePaging.view").forward(request, response);
			return ;
		}
	}
	
}
