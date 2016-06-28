package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.bean.FormatType;
import com.chdw.loc.bean.GroupInfo;
import com.chdw.loc.bean.SdkHttpResult;
import com.chdw.loc.dao.impl.FoodRingMembersDaoImpl;
import com.chdw.loc.domain.FoodRingMembers;
import com.chdw.loc.util.ApiHttpClient;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/FoodRingMembersClientOp")
public class FoodRingMembersClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodRingMembersDaoImpl foodRingMembersDaoImpl;
	private static String key = "3argexb6rnk1e";
	private static String secret = "AgOexcuEA03Q";
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public FoodRingMembersClientOp() throws Exception {
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
		List<FoodRingMembers> frList = foodRingMembersDaoImpl.findAll(where);
		//返回json格式的数据
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(frList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
	}

	//拼凑json字符串
	public String createJsonStr(List<FoodRingMembers> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (FoodRingMembers frm : list) {
			sb.append('{');
			sb.append("frm_id:\"").append(frm.getFrm_id()).append("\",");
			sb.append("u_id:\"").append(frm.getU_id()).append("\",");
			sb.append("user_alias:\"").append(frm.getUser_alias()).append("\",");
			sb.append("user_icon:\"").append(frm.getUser_icon()).append("\",");
			sb.append("user_signature:\"").append(frm.getUser_signature()).append("\",");
			sb.append("fr_id:\"").append(frm.getFr_id()).append("\",");
			sb.append("fr_name:\"").append(frm.getFr_name()).append("\",");
			sb.append("fr_jointime:\"").append(frm.getFr_jointime()).append("\"");
			
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fr_id = request.getParameter("fr_id").trim();
		String u_id = request.getParameter("u_id").trim();
		
		FoodRingMembers frm = new FoodRingMembers();
		frm.setFr_id(fr_id);
		frm.setU_id(u_id);
		
		int result = foodRingMembersDaoImpl.add(frm);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			SdkHttpResult sdkHttpResult = null;
			List<GroupInfo> groups = new ArrayList<GroupInfo>();
			groups.add(new GroupInfo(fr_id, "111"));
			sdkHttpResult = ApiHttpClient.syncGroup(key, secret, u_id, groups, FormatType.json);
System.out.println("同步成功!");
			out.print("1");
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
		
		int result = foodRingMembersDaoImpl.delete(ids);
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

}
