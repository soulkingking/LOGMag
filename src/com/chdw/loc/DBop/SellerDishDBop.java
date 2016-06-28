package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerDishDaoImpl;
import com.chdw.loc.domain.SellerDish;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/SellerDishDBop")
public class SellerDishDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerDishDaoImpl sellerDishDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerDishDBop() throws Exception {
    	sellerDishDaoImpl =  new SellerDishDaoImpl();
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
		String sd_name = request.getParameter("sd_name").trim();
		String sd_salecount = request.getParameter("sd_salecount").trim();
		String sd_price = request.getParameter("sd_price").trim();
		String smt_id = request.getParameter("smt_id").trim();
		
		SellerDish sd = new SellerDish();
		sd.setSd_icon("/upload/SellerDishIcon/default.jpg");
		sd.setSd_name(sd_name);
		sd.setSd_saledCount(Integer.parseInt(sd_salecount));
		sd.setSd_price(Integer.parseInt(sd_price));
		sd.setSmt_id(smt_id);
		
		int result = sellerDishDaoImpl.add(sd);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String sd_id = request.getParameter("sd_id").trim();
		String sd_icon = request.getParameter("sd_icon").trim();
		String sd_name = request.getParameter("sd_name").trim();
		String sd_salecount = request.getParameter("sd_salecount").trim();
		String sd_price = request.getParameter("sd_price").trim();
		
		SellerDish sd = new SellerDish();
		sd.setSd_icon(sd_icon);
		sd.setSd_id(sd_id);
		sd.setSd_name(sd_name);
		sd.setSd_saledCount(Integer.parseInt(sd_salecount));
		sd.setSd_price(Integer.parseInt(sd_price));
		
		int result = sellerDishDaoImpl.update(sd);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		}
		
		int result = sellerDishDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SellerDishPaging.view").forward(request, response);
			return ;
		}
	}
	
}
