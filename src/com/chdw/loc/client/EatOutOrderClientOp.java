package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SeatOrderDaoImpl;
import com.chdw.loc.domain.SeatOrder;

/**
 * Servlet implementation class RestaurantOrderSeatServlet
 */
@WebServlet("/EatOutOrderClientOp")
public class EatOutOrderClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeatOrderDaoImpl seatOrderDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public EatOutOrderClientOp() throws Exception {
		seatOrderDaoImpl = new SeatOrderDaoImpl();
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
		List<SeatOrder> sellerList = seatOrderDaoImpl.findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		return;
	}
	
	/**
	 * 查询
	 * @return
	 */
	public String createJsonStr(List<SeatOrder> list) {
		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
				for (SeatOrder so : list) {
					sb.append('{');
					sb.append("so_id:\"").append(so.getSo_id()).append("\",");
					sb.append("so_time:\"").append(so.getSo_eatTime()).append("\",");
					sb.append("so_count:\"").append(so.getSo_count()).append("\",");
					sb.append("so_extra:\"").append(so.getSo_extra()).append("\",");
					sb.append("so_name:\"").append(so.getSo_name()).append("\",");
					sb.append("so_phone:\"").append(so.getSo_phone()).append("\",");
					sb.append("r_id:\"").append(so.getR_id()).append("\",");
					sb.append("u_id:\"").append(so.getU_id()).append("\",");
					sb.append("r_name:\"").append(so.getR_name()).append("\",");
					sb.append("user_alias:\"").append(so.getUser_alias()).append("\"");
					sb.append("},");
				}
				sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}
		return sb.toString();
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String so_eatTime = request.getParameter("so_eatTime").trim();
		String so_count = request.getParameter("so_count").trim();
		String so_extra = request.getParameter("so_extra").trim();
		String so_name = request.getParameter("so_name").trim();
		String so_phone = request.getParameter("so_phone").trim();
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		SeatOrder so = new SeatOrder();
		so.setSo_count(Integer.parseInt(so_count));
		so.setSo_eatTime(Timestamp.valueOf(so_eatTime));
		so.setSo_extra(so_extra);
		so.setSo_name(so_name);
		so.setSo_phone(so_phone);
		so.setR_id(r_id);
		so.setU_id(u_id);
		
		int result = seatOrderDaoImpl.add(so);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String so_id = request.getParameter("so_id").trim();
		String so_count = request.getParameter("so_count").trim();
		String so_extra = request.getParameter("so_extra").trim();
		String so_name = request.getParameter("so_name").trim();
		String so_phone = request.getParameter("so_phone").trim();
		
		SeatOrder so = new SeatOrder();
		so.setSo_id(so_id);
		so.setSo_count(Integer.parseInt(so_count));
		so.setSo_extra(so_extra);
		so.setSo_name(so_name);
		so.setSo_phone(so_phone);
		
		int result = seatOrderDaoImpl.update(so);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.getRequestDispatcher("/SeatOrderPaging.view").forward(request, response);
			return ;
		}
		
		int result = seatOrderDaoImpl.delete(ids);
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
