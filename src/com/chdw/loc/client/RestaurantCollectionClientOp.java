package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chdw.loc.dao.impl.RestCollectionDaoImpl;
import com.chdw.loc.domain.RestCollection;

/**
 * Servlet implementation class RestaurantCollectionServlet
 */
@WebServlet("/RestaurantCollectionClientOp")
public class RestaurantCollectionClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestCollectionDaoImpl restCollectionDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantCollectionClientOp() throws Exception {
		restCollectionDaoImpl = new RestCollectionDaoImpl();
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
		if (opType == null || opType.equals("")) {
			opType = "";
		}
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		} else if (opType.equals("select")) {
			select(request, response, where);
		}
		return;
	}
	
	private void select(HttpServletRequest request,
			HttpServletResponse response, String where) throws ServletException, IOException {
		// 生成返回对象的集合
		List<RestCollection> sellerList = restCollectionDaoImpl.findAll(where);
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
	public String createJsonStr(List<RestCollection> list) {
		StringBuilder sb = new StringBuilder();
		if(list != null) {
			sb.append('[');
			for (RestCollection restCollection : list) {
				sb.append('{');
				sb.append("rcoll_id:\"").append(restCollection.getRcoll_id()).append("\",");
				sb.append("r_id:\"").append(restCollection.getR_id()).append("\",");
				sb.append("rest_name:\"").append(restCollection.getRest_name()).append("\",");
				sb.append("u_id:\"").append(restCollection.getU_id()).append("\",");
				sb.append("user_alias:\"").append(restCollection.getUser_alias()).append("\"");
				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}

		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		RestCollection rcoll = new RestCollection();
		rcoll.setR_id(r_id);
		rcoll.setU_id(u_id);
		
		int result = restCollectionDaoImpl.add(rcoll);
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
		String rcoll_id = request.getParameter("rcoll_id").trim();
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		RestCollection rcoll = new RestCollection();
		rcoll.setRcoll_id(rcoll_id);
		rcoll.setR_id(r_id);
		rcoll.setU_id(u_id);
		
		int result = restCollectionDaoImpl.update(rcoll);
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
			request.getRequestDispatcher("/RestCollectionPaging.view").forward(request, response);
			return ;
		}
		
		int result = restCollectionDaoImpl.delete(ids);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
}
