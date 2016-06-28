package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RestCommentDaoImpl;
import com.chdw.loc.domain.RestComment;

/**
 * Servlet implementation class restaurantServlet
 */
@WebServlet("/RestCommentClientOp")
public class RestCommentClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestCommentDaoImpl restCommentDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public RestCommentClientOp() throws Exception {
		restCommentDaoImpl  = new RestCommentDaoImpl();
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
			HttpServletResponse response, String where) throws ServletException, IOException {
		List<RestComment> rcs = restCommentDaoImpl.findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(rcs));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		
		return;
	}

	/**
	 * json的拼接
	 * @return
	 */
	public String createJsonStr(List<RestComment> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (RestComment restaurant : list) {
			sb.append('{');
			sb.append("rc_id:\"").append(restaurant.getRc_id()).append("\",");
			sb.append("rc_eat:\"").append(restaurant.getRc_eat()).append("\",");
			sb.append("rc_service:\"").append(restaurant.getRc_service()).append("\",");
			sb.append("rc_env:\"").append(restaurant.getRc_env()).append("\",");
			sb.append("rc_content:\"").append(restaurant.getRc_content()).append("\",");
			sb.append("r_id:\"").append(restaurant.getR_id()).append("\",");
			sb.append("rest_name:\"").append(restaurant.getRest_name()).append("\",");
			sb.append("u_id:\"").append(restaurant.getU_id()).append("\",");
			sb.append("user_alias:\"").append(restaurant.getUser_alias()).append("\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		String rc_eat = request.getParameter("rc_eat").trim();
		String rc_service = request.getParameter("rc_service").trim();
		String rc_env = request.getParameter("rc_env").trim();
		String rc_content = request.getParameter("rc_content").trim();
		
		RestComment rc = new RestComment();
		rc.setR_id(r_id);
		rc.setU_id(u_id);
		rc.setRc_eat(Integer.parseInt(rc_eat));
		rc.setRc_service(Integer.parseInt(rc_service));
		rc.setRc_env(Integer.parseInt(rc_env));
		rc.setRc_content(rc_content);
		
		int result = restCommentDaoImpl.add(rc);
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
		
		String rc_id = request.getParameter("rc_id").trim();
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		String rc_eat = request.getParameter("rc_eat").trim();
		String rc_service = request.getParameter("rc_service").trim();
		String rc_env = request.getParameter("rc_env").trim();
		String rc_content = request.getParameter("rc_content").trim();
		
		RestComment rc = new RestComment();
		rc.setRc_id(rc_id);
		rc.setR_id(r_id);
		rc.setU_id(u_id);
		rc.setRc_eat(Integer.parseInt(rc_eat));
		rc.setRc_service(Integer.parseInt(rc_service));
		rc.setRc_env(Integer.parseInt(rc_env));
		rc.setRc_content(rc_content);
		
		int result = restCommentDaoImpl.update(rc);
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
		
		int result = restCommentDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
}
