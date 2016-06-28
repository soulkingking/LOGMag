package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.DeliveryAddressDaoImpl;
import com.chdw.loc.domain.DeliveryAddress;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/DeliveryAddressDBop")
public class DeliveryAddressDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeliveryAddressDaoImpl deliveryAddressDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryAddressDBop() throws Exception {
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
		
		String opType = request.getParameter("opType").trim();
		
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		}
			
		
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
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
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
		
		int result = deliveryAddressDaoImpl.update(da);
		//System.out.println(result);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
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
		
		int result = deliveryAddressDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/DeliveryAddressPaging.view").forward(request, response);
			return ;
		}
	}
	
}
