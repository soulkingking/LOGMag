package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chdw.loc.dao.impl.SeatInfoDaoImpl;
import com.chdw.loc.domain.SeatInfo;

/**
 * Servlet implementation class RestaurantOrderSeatServlet
 */
@WebServlet("/SeatInfoClientOp")
public class SeatInfoClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeatInfoDaoImpl seatInfoDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public SeatInfoClientOp() throws Exception {
		seatInfoDaoImpl = new SeatInfoDaoImpl();
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
		List<SeatInfo> sellerList = seatInfoDaoImpl.findAll(where);
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
	public String createJsonStr(List<SeatInfo> list) {

		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
				for (SeatInfo seatInfor : list) {
					sb.append('{');
					sb.append("si_id:\"").append(seatInfor.getSi_id()).append("\",");
					sb.append("si_name:\"").append(seatInfor.getSi_name()).append("\",");
					sb.append("si_phone:\"").append(seatInfor.getSi_phone()).append("\",");
					sb.append("u_id:\"").append(seatInfor.getSi_phone()).append("\",");
					sb.append("user_alias:\"").append(seatInfor.getSi_phone()).append("\"");
					sb.append("},");
				}
				sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}
		return sb.toString();
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id = request.getParameter("u_id").trim();
		String si_name = request.getParameter("si_name").trim();
		String si_phone = request.getParameter("si_phone").trim();
		
		SeatInfo si = new SeatInfo();
		si.setU_id(u_id);
		si.setSi_name(si_name);
		si.setSi_phone(si_phone);
		
		int result = seatInfoDaoImpl.add(si);
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
		String si_id = request.getParameter("si_id").trim();
		String si_name = request.getParameter("si_name").trim();
		String si_phone = request.getParameter("si_phone").trim();
		
		SeatInfo si = new SeatInfo();
		si.setSi_id(si_id);
		si.setSi_name(si_name);
		si.setSi_phone(si_phone);
		
		int result = seatInfoDaoImpl.update(si);
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
			request.getRequestDispatcher("/SeatInfoPaging.view").forward(request, response);
			return ;
		}
		
		int result = seatInfoDaoImpl.delete(ids);
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
