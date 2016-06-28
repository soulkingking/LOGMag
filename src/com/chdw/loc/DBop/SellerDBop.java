package com.chdw.loc.DBop;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lxh.smart.SmartUploadException;

import com.chdw.loc.dao.impl.SellerDaoImpl;
import com.chdw.loc.domain.Seller;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class UserInfoAdd
 */
@WebServlet("/SellerDBop")
public class SellerDBop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SellerDaoImpl sellerDaoImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerDBop() throws Exception {
		sellerDaoImpl =  new SellerDaoImpl();
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
		String seller_name = request.getParameter("seller_name").trim();
		String seller_degree = request.getParameter("seller_degree").trim();
		String seller_sendprice = request.getParameter("seller_sendprice").trim();
		String seller_deliverytime = request.getParameter("seller_deliverytime").trim();
		String seller_contact = request.getParameter("seller_contact").trim();
		String seller_starttime = request.getParameter("seller_starttime").trim();
		String seller_endtime = request.getParameter("seller_endtime").trim();
		String seller_status = largerTime(seller_starttime, seller_endtime);
		String seller_notice = request.getParameter("seller_notice").trim();
		String seller_intro = request.getParameter("seller_intro").trim();
		String seller_df = request.getParameter("seller_df").trim();
		String seller_longitude = request.getParameter("seller_longitude").trim();
		String seller_latitude = request.getParameter("seller_latitude").trim();
		Seller seller = new Seller();
		seller.setSeller_icon("/upload/SellerIcon/default.png");
		seller.setSeller_name(seller_name);
		seller.setSeller_degree(Integer.parseInt(seller_degree));
		seller.setSeller_sendprice(Integer.parseInt(seller_sendprice));
		seller.setSeller_deliverytime(Integer.parseInt(seller_deliverytime));
		seller.setSeller_contact(seller_contact);
		seller.setSeller_starttime(seller_starttime);
		seller.setSeller_endtime(seller_endtime);
		seller.setSeller_status(Boolean.parseBoolean(seller_status));
		seller.setSeller_notice(seller_notice);
		seller.setSeller_intro(seller_intro);
		seller.setSeller_df(Integer.parseInt(seller_df));
		seller.setSeller_longitude(Double.parseDouble(seller_longitude));
		seller.setSeller_latitude(Double.parseDouble(seller_latitude));

		int result = sellerDaoImpl.add(seller);
		if (result == 0) {
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		}
	}

	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SmartUploadException {

		String s_id = request.getParameter("s_id").trim();
		String sellerIcon = request.getParameter("sellerIcon").trim();
		String seller_name = request.getParameter("seller_name").trim();
		String seller_degree = request.getParameter("seller_degree").trim();
		String seller_sendprice = request.getParameter("seller_sendprice").trim();
		String seller_deliverytime = request.getParameter("seller_deliverytime").trim();
		String seller_contact = request.getParameter("seller_contact").trim();
		String seller_starttime = request.getParameter("seller_starttime").trim();
		String seller_endtime = request.getParameter("seller_endtime").trim();
		String seller_status = largerTime(seller_starttime, seller_endtime);
		String seller_notice = request.getParameter("seller_notice").trim();
		String seller_intro = request.getParameter("seller_intro").trim();
		String seller_df = request.getParameter("seller_df").trim();
		String seller_longitude = request.getParameter("seller_longitude").trim();
		String seller_latitude = request.getParameter("seller_latitude").trim();
		Seller seller = new Seller();
		seller.setSeller_icon(sellerIcon);
		seller.setS_id(s_id);
		seller.setSeller_name(seller_name);
		seller.setSeller_degree(Integer.parseInt(seller_degree));
		seller.setSeller_sendprice(Integer.parseInt(seller_sendprice));
		seller.setSeller_deliverytime(Integer.parseInt(seller_deliverytime));
		seller.setSeller_contact(seller_contact);
		seller.setSeller_starttime(seller_starttime);
		seller.setSeller_endtime(seller_endtime);
		seller.setSeller_status(Boolean.parseBoolean(seller_status));
		seller.setSeller_notice(seller_notice);
		seller.setSeller_intro(seller_intro);
		seller.setSeller_df(Integer.parseInt(seller_df));
		seller.setSeller_longitude(Double.parseDouble(seller_longitude));
		seller.setSeller_latitude(Double.parseDouble(seller_latitude));

		int result = sellerDaoImpl.update(seller);
		if (result == 0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
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
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		}

		int result = sellerDaoImpl.delete(ids);
		if (result == 0) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		} else if (result == 1) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/SellerPaging.view").forward(request, response);
			return ;
		}
	}
	private static String largerTime(String t1,String t2) 
	{
		Date date1 ,date2,date3;
		DateFormat formart = new SimpleDateFormat("HH:mm");
		try {
			date1 = formart.parse(t1);
			date2 = formart.parse(t2);
			date3= new Date(System.currentTimeMillis());
			int a=date1.getHours()*60+date1.getMinutes();
			int b=date2.getHours()*60+date2.getMinutes();
			int c=date3.getHours()*60+date3.getMinutes();
			if (c>=a&&c<=b) {
				return "true";
			
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "false";
	}
}
