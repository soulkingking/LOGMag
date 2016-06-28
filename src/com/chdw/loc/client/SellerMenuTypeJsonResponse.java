package com.chdw.loc.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerDishDaoImpl;
import com.chdw.loc.dao.impl.SellerMenuTypeDaoImpl;
import com.chdw.loc.domain.SellerDish;
import com.chdw.loc.domain.SellerMenuType;

/**
 * Servlet implementation class SellerDishServlet
 */
@WebServlet("/SellerMenuTypeJsonResponse")
public class SellerMenuTypeJsonResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerMenuTypeJsonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		// 生成返回对象的集合
		List<SellerMenuType> sds = new SellerMenuTypeDaoImpl().findAll(where);

		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sds));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(
				request, response);
		return;

	}

	private String createJsonStr(List<SellerMenuType> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (SellerMenuType smt : list) {
			sb.append('{');
			sb.append("smt_id:\"").append(smt.getSmt_id()).append("\",");
			sb.append("smt_name:\"").append(smt.getSmt_name()).append("\",");
			sb.append("s_id:\"").append(smt.getS_id()).append("\",");
			sb.append("seller_name:\"").append(smt.getSeller_name()).append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

}
