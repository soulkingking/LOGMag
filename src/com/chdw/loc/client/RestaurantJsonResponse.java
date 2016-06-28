package com.chdw.loc.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RestaurantDaoImpl;
import com.chdw.loc.domain.Restaurant;

/**
 * Servlet implementation class restaurantServlet
 */
@WebServlet("/RestaurantJsonResponse")
public class RestaurantJsonResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantJsonResponse() {
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
		
		List<Restaurant> restaurants = new RestaurantDaoImpl().findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(restaurants));
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
	public String createJsonStr(List<Restaurant> list) {

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Restaurant restaurant : list) {
			sb.append('{');
			sb.append("r_id:\"").append(restaurant.getR_id()).append("\",");
			sb.append("r_name:\"").append(restaurant.getR_name()).append("\",");
			sb.append("r_icon:\"").append(restaurant.getR_icon()).append("\",");
			sb.append("r_degree:\"").append(restaurant.getR_degree())
					.append("\",");
			sb.append("r_address:\"").append(restaurant.getR_address())
					.append("\",");
			sb.append("r_starttime:\"").append(restaurant.getR_starttime())
					.append("\",");
			sb.append("r_endtime:\"").append(restaurant.getR_endtime())
					.append("\",");
			sb.append("r_advandays:\"").append(restaurant.getR_advandays())
					.append("\",");
			sb.append("r_intro:\"").append(restaurant.getR_intro())
					.append("\",");
			sb.append("r_contact:\"").append(restaurant.getR_contact())
					.append("\",");
			sb.append("r_status:\"").append(restaurant.isR_status())
					.append("\",");
			sb.append("r_notice:\"").append(restaurant.getR_notice()).append("\",");
			sb.append("r_longitude:\"").append(restaurant.getR_longitude()).append("\",");
			sb.append("r_latitude:\"").append(restaurant.getR_latitude()).append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return sb.toString();
	}
}
