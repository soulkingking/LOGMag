package com.chdw.loc.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SellerDaoImpl;
import com.chdw.loc.domain.Seller;

/**
 * Servlet implementation class SellerServlet
 */
@WebServlet("/SellerJsonResponse")
public class SellerJsonResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerDaoImpl sellerDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerJsonResponse() throws Exception {
		sellerDaoImpl = new SellerDaoImpl();
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
		List<Seller> sellerList = sellerDaoImpl.findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		return;
	}

	/**
	 * json的拼接
	 * 
	 * @param list
	 * @return
	 */
	public String createJsonStr(List<Seller> list) {
		/*
		 * [{id:"43",title:"资讯1",linkaddr:"xxxx1.mp4",timelen:10},
		 * {id:"44",title:"资讯2",linkaddr:"xxxx2.mp4",timelen:30},
		 * {id:"45",title:"资讯3",linkaddr:"xxxx3.mp4",timelen:60}]
		 */

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Seller seller : list) {
			sb.append('{');
			sb.append("s_id:\"").append(seller.getS_id()).append("\",");
			sb.append("seller_icon:\"").append(seller.getSeller_icon())
					.append("\",");
			sb.append("seller_contact:\"").append(seller.getSeller_contact())
					.append("\",");
			sb.append("seller_degree:\"").append(seller.getSeller_degree())
					.append("\",");
			sb.append("seller_deliverytime:\"")
					.append(seller.getSeller_deliverytime()).append("\",");
			sb.append("seller_name:\"").append(seller.getSeller_name())
					.append("\",");
			sb.append("seller_notice:\"").append(seller.getSeller_notice())
					.append("\",");
			sb.append("seller_sendprice:\"")
					.append(seller.getSeller_sendprice()).append("\",");
			sb.append("seller_status:\"").append(seller.isSeller_status())
					.append("\",");
			sb.append("seller_intro:\"").append(seller.getSeller_intro()).append("\",");
			sb.append("seller_starttime:\"").append(seller.getSeller_starttime()).append("\",");
			sb.append("seller_endtime:\"").append(seller.getSeller_endtime()).append("\",");
			sb.append("seller_longitude:\"").append(seller.getSeller_longitude()).append("\",");
			sb.append("seller_latitude:\"").append(seller.getSeller_latitude()).append("\",");
			sb.append("seller_df:\"").append(seller.getSeller_df()).append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return sb.toString();
	}
}
