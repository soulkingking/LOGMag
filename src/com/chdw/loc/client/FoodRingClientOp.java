package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.FoodRingDaoImpl;
import com.chdw.loc.domain.FoodRing;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/FoodRingClientOp")
public class FoodRingClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodRingDaoImpl foodRingDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public FoodRingClientOp() throws Exception {
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
		List<FoodRing> frList = foodRingDaoImpl.findAll(where);
		//返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(frList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
		
	}

	//拼凑json字符串
	public String createJsonStr(List<FoodRing> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (FoodRing fr : list) {
			sb.append('{');
			sb.append("fr_id:\"").append(fr.getFr_id()).append("\",");
			sb.append("fr_icon:\"").append(fr.getFr_icon()).append("\",");
			sb.append("fr_name:\"").append(fr.getFr_name()).append("\",");
			sb.append("fr_createtime:\"").append(fr.getFr_createtime()).append("\",");
			sb.append("fr_visible:\"").append(fr.isFr_visible()).append("\",");
			sb.append("u_id:\"").append(fr.getU_id()).append("\",");
			sb.append("user_alias:\"").append(fr.getUser_alias()).append("\",");
			sb.append("user_icon:\"").append(fr.getUser_icon()).append("\",");
			sb.append("user_signature:\"").append(fr.getUser_signature()).append("\"");
			
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}
	
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fr_name = request.getParameter("fr_name").trim();
		String fr_visible = request.getParameter("fr_visible").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRing fr = new FoodRing();
		fr.setFr_icon("/upload/FoodRingIcon/default.png");
		fr.setFr_name(fr_name);
		fr.setFr_visible(Boolean.parseBoolean(fr_visible));
		fr.setU_id(u_id);
		
		int result = foodRingDaoImpl.add(fr);
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
				sb.append("'").append(string).append("'").append(",");
			}
			ids = sb.toString();
			ids = ids.substring(0, ids.length()-1);
		} else {
			out.print("");
			return ;
		}
		
		int result = foodRingDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

}
