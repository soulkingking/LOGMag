package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.TakeoutOrderDaoImpl;
import com.chdw.loc.domain.TakeoutOrder;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/TakeoutOrderDBop")
public class TakeoutOrderDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TakeoutOrderDaoImpl takeoutOrderDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public TakeoutOrderDBop() throws Exception {
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
		String to_deliveryFee = request.getParameter("to_deliveryFee").trim();
		String to_boxFee = request.getParameter("to_boxFee").trim();
		String to_extra = request.getParameter("to_extra").trim();
		String to_name = request.getParameter("to_name").trim();
		String to_phone = request.getParameter("to_phone").trim();
		String to_address = request.getParameter("to_address").trim();
		String s_id = request.getParameter("s_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		TakeoutOrder to = new TakeoutOrder();
		to.setTo_deliveryFee(Integer.parseInt(to_deliveryFee));
		to.setTo_boxFee(Integer.parseInt(to_boxFee));
		to.setTo_extra(to_extra);
		to.setTo_name(to_name);
		to.setTo_phone(to_phone);
		to.setTo_address(to_address);
		to.setS_id(s_id);
		to.setU_id(u_id);
		
		
		int result = takeoutOrderDaoImpl.add(to);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
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
		
		
		int result = takeoutOrderDaoImpl.update(to);
		//System.out.println(result);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
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
		
		int result = takeoutOrderDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/TakeoutOrderPaging.view").forward(request, response);
			return ;
		}
	}
	
}
