package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.FoodRingMembersDaoImpl;
import com.chdw.loc.domain.FoodRingMembers;

/**
 * Servlet implementation class FoodRingAdd
 */
@WebServlet("/FoodRingMembersDBop")
public class FoodRingMembersDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodRingMembersDaoImpl foodRingMembersDaoImpl;
	
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public FoodRingMembersDBop() throws Exception {
    	foodRingMembersDaoImpl = new FoodRingMembersDaoImpl();
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
		
		String fr_id = request.getParameter("fr_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRingMembers frm = new FoodRingMembers();
		frm.setFr_id(fr_id);
		frm.setU_id(u_id);
		
		int result = foodRingMembersDaoImpl.add(frm);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String frm_id = request.getParameter("frm_id").trim();
		String fr_id = request.getParameter("fr_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRingMembers frm = new FoodRingMembers();
		frm.setFrm_id(frm_id);
		frm.setFr_id(fr_id);
		frm.setU_id(u_id);
		
		int result = foodRingMembersDaoImpl.update(frm);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
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
				sb.append("'").append(string).append("'").append(",");
			}
			ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
		} else {
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
			return ;
		}
		
		int result = foodRingMembersDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/FoodRingMembersPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/foodRingMembers_mag.jsp").forward(request, response);
			return ;
		}
	}

}
