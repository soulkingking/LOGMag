package com.chdw.loc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.impl.AdminDaoImpl;
import com.chdw.loc.util.Md5Encoder;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDaoImpl adminValidDaoImpl;
	//private SellerDaoImpl sellerDaoImpl;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() throws Exception {
    	adminValidDaoImpl = new AdminDaoImpl();
    	//sellerDaoImpl = new SellerDaoImpl();
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
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=utf-8");
		
		String secret = Md5Encoder.encode("admin");
		int result = adminValidDaoImpl.add("admin", secret);
		if (result == 0) {
			request.setAttribute("message", "访问失败!");
			request.getRequestDispatcher("page/register.html").forward(request, response);
		} else if (result == 1) {
			request.getSession().setAttribute("session_LOC_UserLogin_ID", "34567");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (result == 2) {
			request.setAttribute("message", "该用户名已存在!");
			request.getRequestDispatcher("page/register.html").forward(request, response);
		}
		
//		PrintWriter out = response.getWriter();
//		
//		Seller seller = new Seller();
//		seller.setS_id(2);
//		seller.setSeller_name("波波小吃外卖");
//		seller.setSeller_deliverytime(45);
//		seller.setSeller_notice("呵呵");
//		int result = sellerDaoImpl.update(seller);
//		if (result == 1) {
//			out.println("更新成功!");
//		} else if (result == 2) {
//			out.println("更新失败!");
//		} else if (result == 0) {
//			out.println("连接异常!");
//		}
//		
		
//		UserValid uv = new UserValid(3, "user2", "qwerdf456,.");
//		
//		int result = userValidDaoImpl.update(uv);
//		if (result == 1) {
//			out.println("注册成功!");
//		} else if (result == 2) {
//			out.println("用户名已存在!");
//		} else if (result == 2) {
//			out.println("连接异常!");
//		}
		
//		boolean r = userValidDaoImpl.find("user3");
//		if (r) {
//			out.println("用户名已存在!");
//		}
		
		
	}

}
