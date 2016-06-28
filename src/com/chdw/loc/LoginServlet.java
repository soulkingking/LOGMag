package com.chdw.loc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.dao.SellerDao;
import com.chdw.loc.dao.impl.AdminDaoImpl;
import com.chdw.loc.dao.impl.SellerDaoImpl;
import com.chdw.loc.domain.Seller;

/**
 * Servlet implementation class LoginValidServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDaoImpl adminDaoImpl;   
	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() throws Exception {
		adminDaoImpl = new AdminDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname").trim();
		String password = request.getParameter("pword").trim();
		String identity = request.getParameter("Identity");
		if (identity.equals("管理员")) {
			if(request.getSession().getAttribute("identityId")!=null){
				request.getSession().removeAttribute("identityId");
			}
			if(request.getSession().getAttribute("SellerMenuType")!=null) {
				request.getSession().removeAttribute("SellerMenuType");
			}
			int result = adminDaoImpl.validate(username, password);
			if (result == 0) {
				response.getWriter().println("<script>alert(\"访问失败!\");</script>");
			} else if (result == 1) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else if (result == 2 || result == 3) {
				response.getWriter().println("<script>alert(\"用户名或密码错误!\");</script>");
			}	
		}else {
			try {
				SellerDao sellerDao=new SellerDaoImpl();
				List<Seller> sellers=sellerDao.findAll("Where seller_name="+"\'"+username+"\'"+"and seller_contact="+"\'"+password+"\'");
				if (sellers!=null) {
					request.getSession().setAttribute("identityId",sellers.get(0).getS_id());
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
