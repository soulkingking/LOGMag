package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.TakeoutOrderDaoImpl;
import com.chdw.loc.domain.TakeoutOrder;
import com.chdw.loc.util.StringHandler;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/TakeoutOrderClientop")
public class TakeoutOrderClientop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TakeoutOrderDaoImpl takeoutOrderDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public TakeoutOrderClientop() throws Exception {
    	takeoutOrderDaoImpl =  new TakeoutOrderDaoImpl();
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
		// 生成返回对象的集合
		List<TakeoutOrder> sellerList = takeoutOrderDaoImpl.findAll(where);
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
	public String createJsonStr(List<TakeoutOrder> list) {
		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
				for (TakeoutOrder to : list) {
					sb.append('{');
					sb.append("to_id:\"").append(to.getTo_id()).append("\",");
					sb.append("to_df:\"").append(to.getTo_deliveryFee()).append("\",");
					sb.append("to_bf:\"").append(to.getTo_boxFee()).append("\",");
					sb.append("to_extra:\"").append(to.getTo_extra()).append("\",");
					sb.append("to_name:\"").append(to.getTo_name()).append("\",");
					sb.append("to_phone:\"").append(to.getTo_phone()).append("\",");
					sb.append("to_address:\"").append(to.getTo_address()).append("\",");
					sb.append("s_id:\"").append(to.getS_id()).append("\",");
					sb.append("seller_name:\"").append(to.getSeller_name()).append("\",");
					sb.append("u_id:\"").append(to.getU_id()).append("\",");
					sb.append("user_alias:\"").append(to.getUser_alias()).append("\"");
					sb.append("},");
				}
				sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}
		return sb.toString();
	}
	
	

	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String to_deliveryFee = request.getParameter("to_deliveryFee").trim();
		String to_boxFee = request.getParameter("to_boxFee").trim();
		String to_extra = request.getParameter("to_extra").trim();
		String to_name = request.getParameter("to_name").trim();
		String to_phone = request.getParameter("to_phone").trim();
		String to_address = request.getParameter("to_address").trim();
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		TakeoutOrder to = new TakeoutOrder();
		String id = StringHandler.createSerialId(System.currentTimeMillis());
		to.setTo_id(id);
		to.setTo_deliveryFee(Integer.parseInt(to_deliveryFee));
		to.setTo_boxFee(Integer.parseInt(to_boxFee));
		to.setTo_extra(to_extra);
		to.setTo_name(to_name);
		to.setTo_phone(to_phone);
		to.setTo_address(to_address);
		to.setS_id(s_id);
		to.setU_id(u_id);
		
		PrintWriter out = response.getWriter();
		int result = takeoutOrderDaoImpl.add(to);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print(id);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String to_id = request.getParameter("to_id").trim();
		String to_deliveryFee = request.getParameter("to_deliveryFee").trim();
		String to_boxFee = request.getParameter("to_boxFee").trim();
		String to_extra = request.getParameter("to_extra").trim();
		String to_name = request.getParameter("to_name").trim();
		String to_phone = request.getParameter("to_phone").trim();
		String to_address = request.getParameter("to_address").trim();
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		
		TakeoutOrder to = new TakeoutOrder();
		to.setTo_id(to_id);
		to.setTo_deliveryFee(Integer.parseInt(to_deliveryFee));
		to.setTo_boxFee(Integer.parseInt(to_boxFee));
		to.setTo_extra(to_extra);
		to.setTo_name(to_name);
		to.setTo_phone(to_phone);
		to.setTo_address(to_address);
		to.setS_id(s_id);
		to.setU_id(u_id);
		
		PrintWriter out = response.getWriter();
		int result = takeoutOrderDaoImpl.update(to);
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
				sb.append(string).append(",");
			}
			ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
		} else {
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
			return ;
		}
		
		PrintWriter out = response.getWriter();
		int result = takeoutOrderDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
}
