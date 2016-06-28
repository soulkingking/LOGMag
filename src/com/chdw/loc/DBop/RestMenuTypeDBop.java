package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RestMenuTypeDaoImpl;
import com.chdw.loc.domain.RestMenuType;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/RestMenuTypeDBop")
public class RestMenuTypeDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestMenuTypeDaoImpl restMenuTypeDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RestMenuTypeDBop() throws Exception {
    	restMenuTypeDaoImpl =  new RestMenuTypeDaoImpl();
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
		String rmt_name = request.getParameter("rmt_name").trim();
		String r_id = request.getParameter("r_id").trim();
		
		RestMenuType rmt = new RestMenuType();
		rmt.setRmt_name(rmt_name);
		rmt.setR_id(r_id);
		
		int result = restMenuTypeDaoImpl.add(rmt);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rmt_id = request.getParameter("rmt_id").trim();
		String rmt_name = request.getParameter("rmt_name").trim();
		String r_id = request.getParameter("r_id").trim();
		
		RestMenuType rmt = new RestMenuType();
		rmt.setRmt_id(rmt_id);
		rmt.setRmt_name(rmt_name);
		rmt.setRmt_name(r_id);
		
		int result = restMenuTypeDaoImpl.update(rmt);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
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
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		}
		
		int result = restMenuTypeDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/RestMenuTypePaging.view").forward(request, response);
			return ;
		}
	}
	
}
