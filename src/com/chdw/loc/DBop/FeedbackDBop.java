package com.chdw.loc.DBop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.FeedbackDaoImpl;
import com.chdw.loc.domain.Feedback;

/**
 * Servlet implementation class FeedbackDBop
 */
@WebServlet("/FeedbackDBop")
public class FeedbackDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FeedbackDaoImpl feedbackDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackDBop() throws Exception {
    	feedbackDaoImpl = new FeedbackDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        String opType = request.getParameter("opType").trim();
		
		if (opType.equals("add")) {		//添加操作
			add(request, response);
		} else if (opType.equals("delete")) {	//删除操作
			delete(request, response);
		}
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fb_content = request.getParameter("fb_content").trim();
		String u_id = request.getParameter("u_id").trim();
		
		Feedback fb = new Feedback();
		fb.setFb_content(fb_content);
		fb.setU_id(u_id);
		
		int result = feedbackDaoImpl.add(fb);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/FeedbackPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/FeedbackPaging.view").forward(request, response);
			return ;
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.getRequestDispatcher("/FeedbackPaging.view").forward(request, response);
			return ;
		}
		
		int result = feedbackDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/FeedbackPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/FeedbackPaging.view").forward(request, response);
			return ;
		}
		
	}

}
