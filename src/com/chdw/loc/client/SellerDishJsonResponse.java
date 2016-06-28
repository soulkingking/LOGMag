package com.chdw.loc.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerDishDaoImpl;
import com.chdw.loc.domain.SellerDish;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/SellerDishJsonResponse")
public class SellerDishJsonResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerDishDaoImpl sellerDishDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SellerDishJsonResponse() throws Exception {
    	sellerDishDaoImpl = new SellerDishDaoImpl();
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
System.out.println(where);
		
		// 生成返回对象的集合
		List<SellerDish> sds = sellerDishDaoImpl.findAll(where);
		//返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sds));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
	}
	
	//拼凑json字符串
	public String createJsonStr(List<SellerDish> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (SellerDish sd : list) {
			sb.append('{');
			sb.append("sd_id:\"").append(sd.getSd_id()).append("\",");
			sb.append("sd_icon:\"").append(sd.getSd_icon()).append("\",");
			sb.append("sd_name:\"").append(sd.getSd_name()).append("\",");
			sb.append("sd_salecount:\"").append(sd.getSd_saledCount()).append("\",");
			sb.append("sd_price:\"").append(sd.getSd_price()).append("\",");
			sb.append("smt_id:\"").append(sd.getSmt_id()).append("\",");
			sb.append("smt_name:\"").append(sd.getSmt_name()).append("\",");
			sb.append("s_id:\"").append(sd.getS_id()).append("\",");
			sb.append("seller_name:\"").append(sd.getSeller_name()).append("\"");
			
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

}
