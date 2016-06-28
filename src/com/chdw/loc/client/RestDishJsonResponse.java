package com.chdw.loc.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RestDishDaoImpl;
import com.chdw.loc.dao.impl.SellerDishDaoImpl;
import com.chdw.loc.domain.RestDish;
import com.chdw.loc.domain.SellerDish;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/RestDishJsonResponse")
public class RestDishJsonResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestDishDaoImpl restDishDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RestDishJsonResponse() throws Exception {
    	restDishDaoImpl = new RestDishDaoImpl();
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
		
		// 生成返回对象的集合
		List<RestDish> rds = restDishDaoImpl.findAll(where);
		//返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(rds));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
	}
	
	//拼凑json字符串
	public String createJsonStr(List<RestDish> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (RestDish rd : list) {
			sb.append('{');
			sb.append("rd_id:\"").append(rd.getRd_id()).append("\",");
			sb.append("rd_icon:\"").append(rd.getRd_icon()).append("\",");
			sb.append("rd_name:\"").append(rd.getRd_name()).append("\",");
			sb.append("rd_price:\"").append(rd.getRd_price()).append("\",");
			sb.append("rmt_id:\"").append(rd.getRmt_id()).append("\",");
			sb.append("rmt_name:\"").append(rd.getRmt_name()).append("\",");
			sb.append("r_id:\"").append(rd.getR_id()).append("\",");
			sb.append("r_name:\"").append(rd.getR_name()).append("\"");
			
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

}
