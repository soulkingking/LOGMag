package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chdw.loc.dao.impl.DishTableDaoImpl;
import com.chdw.loc.domain.DishTable;

/**
 * Servlet implementation class DeliveryAddressServlet
 */
@WebServlet("/DishTableClientOp")
public class DishTableClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DishTableDaoImpl dishTableDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public DishTableClientOp() throws Exception {
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
		//得到查询条件进行筛选查询
		String where = request.getParameter("condition");
		if (where == null || where.equals("")) {
			where = "";
		}
		
		String opType = request.getParameter("opType").trim();
		if (opType == null || opType.equals("")) {
			opType = "";
		}
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("update")) {	//更新操作
			update(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		} else if (opType.equals("select")) {
			select(request, response, where);
		}
		return;
	}
	
	private void select(HttpServletRequest request,
			HttpServletResponse response, String where) throws ServletException, IOException {
		// 生成返回对象的集合
		List<DishTable> sellerList = dishTableDaoImpl.findAll(where);
		// 返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request,
				response);
		return;
	}

	/**
	 * 查询
	 * @return
	 */
	public String createJsonStr(List<DishTable> list) {

		StringBuilder sb = new StringBuilder();
		if(list != null){
			sb.append('[');
				for (DishTable dt : list) {
					sb.append('{');
					sb.append("dt_id:\"").append(dt.getDt_id()).append("\",");
					sb.append("dt_count:\"").append(dt.getDt_count()).append("\",");
					sb.append("dt_total:\"").append(dt.getDt_total()).append("\",");
					sb.append("to_id:\"").append(dt.getTo_id()).append("\",");
					sb.append("u_id:\"").append(dt.getU_id()).append("\",");
					sb.append("user_alias:\"").append(dt.getUser_alias()).append("\",");
					sb.append("sd_id:\"").append(dt.getSd_id()).append("\",");
					sb.append("sd_name:\"").append(dt.getSd_name()).append("\"");
					sb.append("},");
				}
				sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
		}
		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dt_total = request.getParameter("dt_total").trim();
		String dt_count = request.getParameter("dt_count").trim();
		String to_id = request.getParameter("to_id").trim();
		String sd_id = request.getParameter("sd_id").trim();
		
		DishTable dt = new DishTable();
		dt.setDt_total(Integer.parseInt(dt_total));
		dt.setDt_count(Integer.parseInt(dt_count));
		dt.setTo_id(to_id);
		dt.setSd_id(sd_id);
		
		int result = dishTableDaoImpl.add(dt);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
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
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
			out.print("");
			return ;
		}
		
		int result = dishTableDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

}
