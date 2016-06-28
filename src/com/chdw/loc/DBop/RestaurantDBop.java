package com.chdw.loc.DBop;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.RestaurantDaoImpl;
import com.chdw.loc.domain.Restaurant;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/RestaurantDBop")
public class RestaurantDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestaurantDaoImpl restaurantDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantDBop() throws Exception {
    	restaurantDaoImpl =  new RestaurantDaoImpl();
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
		
		String r_name = request.getParameter("r_name").trim();
		String r_degree = request.getParameter("r_degree").trim();
		String r_address = request.getParameter("r_address").trim();
		String r_contact = request.getParameter("r_contact").trim();
		String r_advandays = request.getParameter("r_advandays").trim();
		String r_starttime = request.getParameter("r_starttime").trim();
		String r_endtime = request.getParameter("r_endtime").trim();
		String r_status = request.getParameter("r_status").trim();
		String r_notice = request.getParameter("r_notice").trim();
		String r_intro = request.getParameter("r_intro").trim();
		String r_longitude = request.getParameter("r_longitude").trim();
		String r_latitude = request.getParameter("r_latitude").trim();
		
		Restaurant rest = new Restaurant();
		rest.setR_icon("/upload/RestIcon/default.jpg");
		rest.setR_name(r_name);
		rest.setR_degree(Integer.parseInt(r_degree));
		rest.setR_address(r_address);
		rest.setR_contact(r_contact);
		rest.setR_advandays(Integer.parseInt(r_advandays));
		rest.setR_starttime(Time.valueOf(r_starttime));
		rest.setR_endtime(Time.valueOf(r_endtime));
		rest.setR_status(Boolean.parseBoolean(r_status));
		rest.setR_notice(r_notice);
		rest.setR_intro(r_intro);
		rest.setR_longitude(Double.parseDouble(r_longitude));
		rest.setR_latitude(Double.parseDouble(r_latitude));
		
		int result = restaurantDaoImpl.add(rest);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		}
	}
	
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String r_id = request.getParameter("r_id").trim();
		String r_icon = request.getParameter("r_icon").trim();
		String r_name = request.getParameter("r_name").trim();
		String r_degree = request.getParameter("r_degree").trim();
		String r_address = request.getParameter("r_address").trim();
		String r_contact = request.getParameter("r_contact").trim();
		String r_advandays = request.getParameter("r_advandays").trim();
		String r_starttime = request.getParameter("r_starttime").trim();
		String r_endtime = request.getParameter("r_endtime").trim();
		String r_status = request.getParameter("r_status").trim();
		String r_notice = request.getParameter("r_notice").trim();
		String r_intro = request.getParameter("r_intro").trim();
		String r_longitude = request.getParameter("r_longitude").trim();
		String r_latitude = request.getParameter("r_latitude").trim();
		
		Restaurant rest = new Restaurant();
		rest.setR_id(r_id);
		rest.setR_icon(r_icon);
		rest.setR_name(r_name);
		rest.setR_degree(Integer.parseInt(r_degree));
		rest.setR_address(r_address);
		rest.setR_contact(r_contact);
		rest.setR_advandays(Integer.parseInt(r_advandays));
		rest.setR_starttime(Time.valueOf(r_starttime));
		rest.setR_endtime(Time.valueOf(r_endtime));
		rest.setR_status(Boolean.parseBoolean(r_status));
		rest.setR_notice(r_notice);
		rest.setR_intro(r_intro);
		rest.setR_longitude(Double.parseDouble(r_longitude));
		rest.setR_latitude(Double.parseDouble(r_latitude));
		
		int result = restaurantDaoImpl.update(rest);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		}
		
		int result = restaurantDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/RestaurantPaging.view").forward(request, response);
			return ;
		}
	}
	
}
