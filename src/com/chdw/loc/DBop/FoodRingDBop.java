package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.FoodRingDaoImpl;
import com.chdw.loc.domain.FoodRing;
import com.chdw.loc.util.StringHandler;

/**
 * Servlet implementation class FoodRingAdd
 */
@WebServlet("/FoodRingDBop")
public class FoodRingDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodRingDaoImpl foodRingDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public FoodRingDBop() throws Exception {
    	foodRingDaoImpl = new FoodRingDaoImpl();
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
		String fr_name = request.getParameter("fr_name").trim();
		String fr_visible = request.getParameter("fr_visible").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRing fr = new FoodRing();
		String id = StringHandler.createSerialId(System.currentTimeMillis());
		fr.setFr_id(id);
		fr.setFr_icon("/upload/FoodRingIcon/default.png");
		fr.setFr_name(fr_name);
		fr.setFr_visible(Boolean.parseBoolean(fr_visible));
		fr.setU_id(u_id);
		
		int result = foodRingDaoImpl.add(fr);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SmartUploadException {
		
		String frIcon = request.getParameter("frIcon").trim();
		String fr_id = request.getParameter("fr_id").trim();
		String fr_name = request.getParameter("fr_name").trim();
		String fr_visible = request.getParameter("fr_visible").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRing fr = new FoodRing();
		fr.setFr_icon(frIcon);
		fr.setFr_id(fr_id);
		fr.setFr_name(fr_name);
		fr.setFr_visible(Boolean.parseBoolean(fr_visible));
		fr.setU_id(u_id);
		
		int result = foodRingDaoImpl.update(fr);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		}
		
		int result = foodRingDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/FoodRingPaging.view").forward(request, response);
			return ;
		}
	}

}
