package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.TakeoutOrderStatusDaoImpl;
import com.chdw.loc.domain.TakeoutOrderStatus;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/TakeoutOrderStatusDBop")
public class TakeoutOrderStatusDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TakeoutOrderStatusDaoImpl takeoutOrderStatusDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeoutOrderStatusDBop() throws Exception {
		takeoutOrderStatusDaoImpl =  new TakeoutOrderStatusDaoImpl();
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
		String tos_status = request.getParameter("tos_status").trim();
		String to_id = request.getParameter("to_id").trim();

		TakeoutOrderStatus tos = new TakeoutOrderStatus();
		tos.setTos_status(tos_status);
		tos.setTo_id(to_id);

		int result = takeoutOrderStatusDaoImpl.add(tos);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/deliveryAddress_mag.jsp").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/TakeoutOrderStatusPaging.view").forward(request, response);
			return ;
		}
	}

	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tos_id=request.getParameter("tos_id").trim();
		String tos_status = request.getParameter("tos_status").trim();
		String to_id = request.getParameter("to_id").trim();

		System.out.println(tos_status);
		TakeoutOrderStatus tos = new TakeoutOrderStatus();
		tos.setTos_id(tos_id);
		tos.setTos_status(tos_status);
		tos.setTo_id(to_id);
		int result = takeoutOrderStatusDaoImpl.update(tos);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/deliveryAddress_mag.jsp").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/TakeoutOrderStatusPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/TakeoutOrderStatusPaging.view").forward(request, response);
			return ;
		}

		int result = takeoutOrderStatusDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/deliveryAddress_mag.jsp").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/TakeoutOrderStatusPaging.view").forward(request, response);
			return ;
		}
	}

}
