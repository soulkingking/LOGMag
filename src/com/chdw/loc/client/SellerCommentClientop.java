package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerCommentDaoImpl;
import com.chdw.loc.domain.SellerComment;

@WebServlet("/SellerCommentClientop")
public class SellerCommentClientop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerCommentDaoImpl sellerCommentDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerCommentClientop() throws Exception {
    	sellerCommentDaoImpl =  new SellerCommentDaoImpl();
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
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		
		String opType = request.getParameter("opType").trim();
		
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		} else if (opType.equals("select")) {
			select(request, response, where);
		}
			
	}
	
	private void select(HttpServletRequest request,
			HttpServletResponse response, String where) throws Exception {
		List<SellerComment> scs = new SellerCommentDaoImpl().findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(scs));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		return;
	}
	
	/**
	 * json的拼接
	 * @return
	 */
	public String createJsonStr(List<SellerComment> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (SellerComment seller : list) {
			sb.append('{');
			sb.append("sc_id:\"").append(seller.getSc_id()).append("\",");
			sb.append("sc_eat:\"").append(seller.getSc_eat()).append("\",");
			sb.append("sc_service:\"").append(seller.getSc_service())
					.append("\",");
			sb.append("sc_content:\"").append(seller.getSc_content())
					.append("\",");
			sb.append("s_id:\"").append(seller.getS_id()).append("\",");
			sb.append("seller_name:\"").append(seller.getSeller_name())
					.append("\",");
			sb.append("u_id:\"").append(seller.getU_id()).append("\",");
			sb.append("user_alias:\"").append(seller.getUser_alias())
					.append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return sb.toString();
	}

	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		String sc_eat = request.getParameter("sc_eat").trim();
		String sc_service = request.getParameter("sc_service").trim();
		String sc_content = request.getParameter("sc_content").trim();
		
		SellerComment sc = new SellerComment();
		sc.setS_id(s_id);
		sc.setU_id(u_id);
		sc.setSc_eat(Integer.parseInt(sc_eat));
		sc.setSc_service(Integer.parseInt(sc_service));
		sc.setSc_content(sc_content);
		
		int result = sellerCommentDaoImpl.add(sc);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String sc_id = request.getParameter("sc_id").trim();
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		String sc_eat = request.getParameter("sc_eat").trim();
		String sc_service = request.getParameter("sc_service").trim();
		String sc_content = request.getParameter("sc_content").trim();
		
		SellerComment sc = new SellerComment();
		sc.setSc_id(sc_id);
		sc.setS_id(s_id);
		sc.setU_id(u_id);
		sc.setSc_eat(Integer.parseInt(sc_eat));
		sc.setSc_service(Integer.parseInt(sc_service));
		sc.setSc_content(sc_content);
		
		int result = sellerCommentDaoImpl.update(sc);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
			out.print("");
			return ;
		}
		
		int result = sellerCommentDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
}
