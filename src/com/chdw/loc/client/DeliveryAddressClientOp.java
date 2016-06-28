package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.DeliveryAddressDaoImpl;
import com.chdw.loc.domain.DeliveryAddress;

/**
 * Servlet implementation class DeliveryAddressServlet
 */
@WebServlet("/DeliveryAddressClientOp")
public class DeliveryAddressClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeliveryAddressDaoImpl deliveryAddressDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryAddressClientOp() throws Exception {
        deliveryAddressDaoImpl =  new DeliveryAddressDaoImpl();
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
		List<DeliveryAddress> sellerList = deliveryAddressDaoImpl.findAll(where);
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
	public String createJsonStr(List<DeliveryAddress> list) {

		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
				for (DeliveryAddress deliveryAddress : list) {
					sb.append('{');
					sb.append("da_id:\"").append(deliveryAddress.getDa_id()).append("\",");
					sb.append("da_name:\"").append(deliveryAddress.getDa_name()).append("\",");
					sb.append("da_phone:\"").append(deliveryAddress.getDa_phone()).append("\",");
					sb.append("da_address:\"").append(deliveryAddress.getDa_address()).append("\"");
					sb.append("},");
				}
				sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}
		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String da_name = request.getParameter("da_name").trim();
		String da_phone = request.getParameter("da_phone").trim();
		String da_address = request.getParameter("da_address").trim();
		String u_id = request.getParameter("u_id").trim();
		
		DeliveryAddress da = new DeliveryAddress();
		da.setDa_name(da_name);
		da.setDa_phone(da_phone);
		da.setDa_address(da_address);
		da.setU_id(u_id);
		
		int result = deliveryAddressDaoImpl.add(da);
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
		String da_id=request.getParameter("da_id").trim();
		String da_name = request.getParameter("da_name").trim();
		String da_phone = request.getParameter("da_phone").trim();
		String da_address = request.getParameter("da_address").trim();
		String u_id = request.getParameter("u_id").trim();
		DeliveryAddress da = new DeliveryAddress();
		da.setDa_id(da_id);
		da.setDa_name(da_name);
		da.setDa_phone(da_phone);
		da.setDa_address(da_address);
		da.setU_id(u_id);
		
		PrintWriter out = response.getWriter();
		int result = deliveryAddressDaoImpl.update(da);
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
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
			return ;
		}
		
		PrintWriter out = response.getWriter();
		int result = deliveryAddressDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	

}
