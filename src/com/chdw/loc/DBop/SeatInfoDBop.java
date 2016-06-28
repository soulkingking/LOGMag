package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.SeatInfoDaoImpl;
import com.chdw.loc.domain.SeatInfo;

/**
 * Servlet implementation class SeatInfoDBop
 */
@WebServlet("/SeatInfoDBop")
public class SeatInfoDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SeatInfoDaoImpl seatInfoDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public SeatInfoDBop() throws Exception {
        seatInfoDaoImpl = new SeatInfoDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String opType = request.getParameter("opType").trim();
		
		if (opType.equals("add")) {
			add(request,response);
		} else if (opType.equals("update")) {
			update(request,response);
		} else if (opType.equals("delete")) {
			delete(request,response);
		}
		
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
		if (result == 0) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("SeatInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("SeatInfoPaging.view").forward(request, response);
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
		if (result == 0) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("SeatInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("SeatInfoPaging.view").forward(request, response);
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
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SeatInfoPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SeatInfoPaging.view").forward(request, response);
			return ;
		}
	}
	
}
