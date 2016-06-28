package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RemarkDaoImpl;
import com.chdw.loc.domain.Remark;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/RemarkDBop")
public class RemarkDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RemarkDaoImpl remarkDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RemarkDBop() throws Exception {
    	remarkDaoImpl =  new RemarkDaoImpl();
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
		
		String u_id = request.getParameter("u_id").trim();
		String fr_id = request.getParameter("fr_id").trim();
		String re_userid = request.getParameter("re_userid").trim();
		String re_name = request.getParameter("re_name").trim();
		
		Remark remark = new Remark();
		remark.setU_id(u_id);
		remark.setFr_id(fr_id);
		remark.setRemarked_id(re_userid);
		remark.setRe_name(re_name);
		
		int result = remarkDaoImpl.add(remark);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String re_id = request.getParameter("re_id").trim();
		String u_id = request.getParameter("u_id").trim();
		String fr_id = request.getParameter("fr_id").trim();
		String re_userid = request.getParameter("re_userid").trim();
		String re_name = request.getParameter("re_name").trim();
		
		Remark remark = new Remark();
		remark.setRe_id(re_id);
		remark.setU_id(u_id);
		remark.setFr_id(fr_id);
		remark.setRemarked_id(re_userid);
		remark.setRe_name(re_name);
		
		int result = remarkDaoImpl.update(remark);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		}
		
		int result = remarkDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/RemarkPaging.view").forward(request, response);
			return ;
		}
	}
	
}
