package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.DishTableDaoImpl;
import com.chdw.loc.domain.DishTable;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/DishTableDBop")
public class DishTableDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DishTableDaoImpl dishTableDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public DishTableDBop() throws Exception {
    	dishTableDaoImpl =  new DishTableDaoImpl();
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
		String d_count = request.getParameter("d_count").trim();
		String to_id = request.getParameter("to_id").trim();
		String sd_id = request.getParameter("sd_id").trim();
		
		DishTable dt = new DishTable();
		dt.setDt_count(Integer.parseInt(d_count));
		dt.setTo_id(to_id);
		dt.setSd_id(sd_id);
		
		int result = dishTableDaoImpl.add(dt);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dt_id=request.getParameter("dt_id").trim();
		String d_count = request.getParameter("d_count").trim();
		String to_id = request.getParameter("to_id").trim();
		String sd_id = request.getParameter("sd_id").trim();
		DishTable dt = new DishTable();
		dt.setDt_id(dt_id);
		dt.setDt_count(Integer.parseInt(d_count));
		dt.setTo_id(to_id);
		dt.setSd_id(sd_id);
		
		int result = dishTableDaoImpl.update(dt);
		//System.out.println(result);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
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
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		}
		
		int result = dishTableDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/DishTablePaging.view").forward(request, response);
			return ;
		}
	}
	
}
