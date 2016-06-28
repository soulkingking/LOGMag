package com.chdw.loc.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.AccountPointDaoImpl;
import com.chdw.loc.domain.AccountPoint;

/**
 * Servlet implementation class UserInfoResponse
 */
@WebServlet("/AccountPointClientOp")
public class AccountPointClientOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountPointDaoImpl accountPointDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public AccountPointClientOp() throws Exception {
    	accountPointDaoImpl = new AccountPointDaoImpl();
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
		List<AccountPoint> sellerList = accountPointDaoImpl.findAll(where);
		// 返回json数据用jsp页面生成
		request.setAttribute("jsonData", createJsonStr(sellerList));
		request.getRequestDispatcher("/json/SendJson.jsp").forward(request, response);
		return ;
	}

	//拼凑json字符串
	public String createJsonStr(List<AccountPoint> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (AccountPoint ap : list) {
			sb.append('{');
			sb.append("ap_id:\"").append(ap.getAp_id()).append("\",");
			sb.append("ap_curpoint:\"").append(ap.getAp_curpoint()).append("\",");
			sb.append("ap_maxpoint:\"").append(ap.getAp_maxpoint()).append("\",");
			sb.append("ap_maxWeekRank:\"").append(ap.getAp_maxWeekRank()).append("\",");
			sb.append("ap_maxMonthRank:\"").append(ap.getAp_maxMonthRank()).append("\",");
			sb.append("ap_maxRank:\"").append(ap.getAp_maxRank()).append("\",");
			sb.append("ap_curWeekDif:\"").append(ap.getAp_curWeekDif()).append("\",");
			sb.append("ap_curMonthDif:\"").append(ap.getAp_curMonthDif()).append("\",");
			sb.append("u_id:\"").append(ap.getU_id()).append("\",");
			sb.append("user_alias:\"").append(ap.getUser_alias()).append("\",");
			sb.append("user_icon:\"").append(ap.getUser_icon()).append("\"");
			
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String ap_curpoint = request.getAttribute("ap_curpoint").toString().trim();
		String ap_maxpoint = request.getAttribute("ap_maxpoint").toString().trim();
		String ap_maxWeekRank = request.getAttribute("ap_maxWeekRank").toString().trim();
		String ap_maxMonthRank = request.getAttribute("ap_maxMonthRank").toString().trim();
		String ap_maxRank = request.getAttribute("ap_maxRank").toString().trim();
		String ap_curWeekDif = request.getAttribute("ap_curWeekDif").toString().trim();
		String ap_curMonthDif = request.getAttribute("ap_curMonthDif").toString().trim();
		String u_id = request.getAttribute("u_id").toString().trim();
		
		AccountPoint ap = new AccountPoint();
		ap.setAp_curpoint(Integer.parseInt(ap_curpoint));
		ap.setAp_maxpoint(Integer.parseInt(ap_maxpoint));
		ap.setAp_maxWeekRank(Integer.parseInt(ap_maxWeekRank));
		ap.setAp_maxMonthRank(Integer.parseInt(ap_maxMonthRank));
		ap.setAp_maxRank(Integer.parseInt(ap_maxRank));
		ap.setAp_curWeekDif(Integer.parseInt(ap_curWeekDif));
		ap.setAp_curMonthDif(Integer.parseInt(ap_curMonthDif));
		ap.setU_id(u_id);
		
		int result = accountPointDaoImpl.add(ap);
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
		
		String ap_id = request.getParameter("ap_id").trim();
		String ap_curpoint = request.getParameter("ap_curpoint").trim();
		String ap_maxpoint = request.getParameter("ap_maxpoint").trim();
		String ap_maxWeekRank = request.getParameter("ap_maxWeekRank").trim();
		String ap_maxMonthRank = request.getParameter("ap_maxMonthRank").trim();
		String ap_maxRank = request.getParameter("ap_maxRank").trim();
		String ap_curWeekDif = request.getParameter("ap_curWeekDif").trim();
		String ap_curMonthDif = request.getParameter("ap_curMonthDif").trim();
		
		AccountPoint ap = new AccountPoint();
		ap.setAp_id(ap_id);
		ap.setAp_curpoint(Integer.parseInt(ap_curpoint));
		ap.setAp_maxpoint(Integer.parseInt(ap_maxpoint));
		ap.setAp_maxWeekRank(Integer.parseInt(ap_maxWeekRank));
		ap.setAp_maxMonthRank(Integer.parseInt(ap_maxMonthRank));
		ap.setAp_maxRank(Integer.parseInt(ap_maxRank));
		ap.setAp_curWeekDif(Integer.parseInt(ap_curWeekDif));
		ap.setAp_curMonthDif(Integer.parseInt(ap_curMonthDif));
		
		int result = accountPointDaoImpl.update(ap);
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
		
		int result = accountPointDaoImpl.delete(ids);
		
		if (result == 0) {
			out.print("");
			return ;
		} else if (result == 1) {
			out.print("1");
			return ;
		}
	}

}
