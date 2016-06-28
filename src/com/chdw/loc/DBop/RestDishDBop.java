package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.RestDishDaoImpl;
import com.chdw.loc.domain.RestDish;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/RestDishDBop")
public class RestDishDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestDishDaoImpl restDishDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RestDishDBop() throws Exception {
    	restDishDaoImpl =  new RestDishDaoImpl();
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
		
		String rd_name = request.getParameter("rd_name").trim();
		String rd_price = request.getParameter("rd_price").trim();
		String rmt_id = request.getParameter("rmt_id").trim();
		
		RestDish rd = new RestDish();
		rd.setRd_icon("/upload/RestDishIcon/default.jpg");
		rd.setRd_name(rd_name);
		rd.setRd_price(Integer.parseInt(rd_price));
		rd.setRmt_id(rmt_id);
		
		int result = restDishDaoImpl.add(rd);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SmartUploadException {
		
		String rd_id = request.getParameter("rd_id").trim();
		String rd_icon = request.getParameter("rd_icon").trim();
		String rd_name = request.getParameter("rd_name").trim();
		String rd_price = request.getParameter("rd_price").trim();
		String rmt_id = request.getParameter("rmt_id").trim();
		
		RestDish rd = new RestDish();
		rd.setRd_icon(rd_icon);
		rd.setRd_id(rd_id);
		rd.setRd_name(rd_name);
		rd.setRd_price(Integer.parseInt(rd_price));
		rd.setRmt_id(rmt_id);
		
		int result = restDishDaoImpl.update(rd);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		}
		
		int result = restDishDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/RestDishPaging.view").forward(request, response);
			return ;
		}
	}
	
}
