package com.fmeal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chdw.loc.domain.BuyOrder;
import com.chdw.loc.domain.Car;
import com.chdw.loc.domain.Seller;
import com.chdw.loc.domain.TakeoutOrder;
import com.chdw.loc.domain.TakeoutOrderStatus;
import com.chdw.loc.util.GetAndroidJson;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.FMealOrderDao;
import com.fmeal.daoimpl.FMealOrderDaoImpl;

/**
 * Servlet implementation class FMealOrderServlet
 */
@WebServlet("/FMealOrderServlet")
public class FMealOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		BuyOrder buyOrder=(BuyOrder) GsonUtil.fromJson(GetAndroidJson.getAndroidJson(request), BuyOrder.class);
		List<Car> cars=buyOrder.getCars();
		TakeoutOrder takeoutOrder=buyOrder.getTakeoutOrder();
		Seller seller=buyOrder.getSeller();
		TakeoutOrderStatus takeoutOrderStatus=buyOrder.getTakeoutOrderStatus();
		FMealOrderDao fMealOrderDao=new FMealOrderDaoImpl();
		boolean isSuccess=fMealOrderDao.addTakeOrder(takeoutOrder);
		if (isSuccess) {
			fMealOrderDao.addTakeOrderStatus(takeoutOrderStatus);
			for (Car car : cars) {
				fMealOrderDao.updatesaledCount(car.getSellerDish().getSd_id(), car.getNum());
				isSuccess=fMealOrderDao.addTakeOrderInfo(takeoutOrder.getTo_id(), car);
			}
		}
		if (isSuccess) {
			BuyOrder order=fMealOrderDao.findBuyOrder(takeoutOrder.getTo_id(),takeoutOrder.getU_id());
			response.getWriter().print(GsonUtil.toJson(order));
		}
	}

}
