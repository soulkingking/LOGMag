package com.fmeal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.DeliveryAddress;
import com.chdw.loc.util.GetAndroidJson;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealDeliveryAddressDao;
import com.fmeal.daoimpl.FMealDeliveryAddressDaoImpl;

/**
 * Servlet implementation class FMealUpdateDeliveryAddressServlet
 */
@WebServlet("/FMealUpdateDeliveryAddressServlet")
public class FMealUpdateDeliveryAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		DeliveryAddress deliveryAddress=(DeliveryAddress) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request), DeliveryAddress.class);
		FMealDeliveryAddressDao fMealDeliveryAddressDao=new FMealDeliveryAddressDaoImpl();
		List<DeliveryAddress> deliveryAddresses=fMealDeliveryAddressDao.updateDeliveryAddress(deliveryAddress);
		response.getWriter().print(GsonUtil.toJson(deliveryAddresses));
	}

}
