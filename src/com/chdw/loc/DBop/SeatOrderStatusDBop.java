package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SeatOrderStatusDaoImpl;
import com.chdw.loc.domain.SeatOrderStatus;

/**
 * Servlet implementation class SeatOrderStatusDBop
 */
@WebServlet("/SeatOrderStatusDBop")
public class SeatOrderStatusDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SeatOrderStatusDaoImpl seatOrderStatusDaoImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public SeatOrderStatusDBop() throws Exception {
		seatOrderStatusDaoImpl = new SeatOrderStatusDaoImpl();
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

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sos_status = request.getParameter("sos_status").trim();
		String so_id = request.getParameter("so_id").trim();

		SeatOrderStatus sos = new SeatOrderStatus();
		sos.setSos_status(sos_status);
		sos.setSo_id(so_id);

		int result = seatOrderStatusDaoImpl.add(sos);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(
					request, response);
			return;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(
					request, response);
			return;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sos_id = request.getParameter("sos_id").trim();
		String sos_status = request.getParameter("sos_status").trim();

		SeatOrderStatus sos = new SeatOrderStatus();
		sos.setSos_id(sos_id);
		sos.setSos_status(sos_status);

		int result = seatOrderStatusDaoImpl.update(sos);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(
					request, response);
			return;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(
					request, response);
			return;
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
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(request, response);
			return ;
		}
		
		int result = seatOrderStatusDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SeatOrderStatusPaging.view").forward(request, response);
			return ;
		}
	}

}
