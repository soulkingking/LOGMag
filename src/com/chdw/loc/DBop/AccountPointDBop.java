package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.AccountPointDaoImpl;
import com.chdw.loc.domain.AccountPoint;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/AccountPointDBop")
public class AccountPointDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountPointDaoImpl accountPointDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public AccountPointDBop() throws Exception {
    	accountPointDaoImpl =  new AccountPointDaoImpl();
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
		
		String ap_curpoint = request.getParameter("ap_curpoint").trim();
		String ap_maxpoint = request.getParameter("ap_maxpoint").trim();
		String ap_maxWeekRank = request.getParameter("ap_maxWeekRank").trim();
		String ap_maxMonthRank = request.getParameter("ap_maxMonthRank").trim();
		String ap_maxRank = request.getParameter("ap_maxRank").trim();
		String ap_curWeekDif = request.getParameter("ap_curWeekDif").trim();
		String ap_curMonthDif = request.getParameter("ap_curMonthDif").trim();
		String u_id = request.getParameter("u_id").trim();
		
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
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
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
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
			return ;
		}
		
		int result = accountPointDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/AccountPointPaging.view").forward(request, response);
			return ;
		}
	}
	
}
