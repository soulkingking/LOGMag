package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SeatOrderDaoImpl;
import com.chdw.loc.domain.SeatOrder;

/**
 * Servlet implementation class SeatOrderDBop
 */
@WebServlet("/SeatOrderDBop")
public class SeatOrderDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SeatOrderDaoImpl seatOrderDaoImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public SeatOrderDBop() throws Exception {
		seatOrderDaoImpl = new SeatOrderDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String opType = request.getParameter("opType").trim();

		if (opType.equals("add")) { // 添加操作
			add(request, response);
		} else if (opType.equals("update")) { // 更新操作
			update(request, response);
		} else if (opType.equals("delete")) { // 删除操作
			delete(request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String so_count = request.getParameter("so_count").trim();
		String so_extra = request.getParameter("so_extra").trim();
		String so_name = request.getParameter("so_name").trim();
		String so_phone = request.getParameter("so_phone").trim();
		String r_id = request.getParameter("r_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		SeatOrder so = new SeatOrder();
		so.setSo_count(Integer.parseInt(so_count));
		so.setSo_extra(so_extra);
		so.setSo_name(so_name);
		so.setSo_phone(so_phone);
		so.setR_id(r_id);
		so.setU_id(u_id);
		
		int result = seatOrderDaoImpl.add(so);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SeatOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SeatOrderPaging.view").forward(request, response);
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
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("SeatOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("SeatOrderPaging.view").forward(request, response);
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
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SeatOrderPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SeatOrderPaging.view").forward(request, response);
			return ;
		}
	}
}
