package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet("/SellerCollectionClientop")
public class SellerCollectionClientop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerCollectionDaoImpl sellerCollectionDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerCollectionClientop() throws Exception {
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
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		
		String opType = request.getParameter("opType").trim();
		if (opType == null || opType.equals("")) {
			opType = "";
		}
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("select")) {	//更新操作
			select(request, response, where);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		} 
			
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response, String where) throws IOException, ServletException {
System.out.println(where);
		// 生成返回对象的集合
		List<SellerCollection> sellerList = sellerCollectionDaoImpl.findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		return;
	}
	
	/**
	 * json的拼接
	 * @return
	 */
	public String createJsonStr(List<SellerCollection> list) {
		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
			for (SellerCollection seller : list) {
				sb.append('{');
				sb.append("scoll_id:\"").append(seller.getScoll_id()).append("\",");
				sb.append("s_id:\"").append(seller.getS_id()).append("\",");
				sb.append("u_id:\"").append(seller.getU_id()).append("\",");
				sb.append("seller_name:\"").append(seller.getSeller_name()).append("\",");
				sb.append("user_alias:\"").append(seller.getUser_alias()).append("\"");
				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}

		return sb.toString();
	}

	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		PrintWriter out = response.getWriter();
		SellerCollection scoll = new SellerCollection();
		scoll.setS_id(s_id);
		scoll.setU_id(u_id);
		
		int result = sellerCollectionDaoImpl.add(scoll);
		if (result == 0) {
			out.print("");
		} else if (result == 1) {
			out.print("1");
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
		
		int result = sellerCollectionDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
}
