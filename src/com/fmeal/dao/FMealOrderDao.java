package com.fmeal.dao;

import java.util.List;

import com.chdw.loc.domain.BuyOrder;
import com.chdw.loc.domain.Car;
import com.chdw.loc.domain.TakeoutOrder;
import com.chdw.loc.domain.TakeoutOrderStatus;

public interface FMealOrderDao {
	boolean addTakeOrder(TakeoutOrder takeoutOrder);

	boolean addTakeOrderStatus(TakeoutOrderStatus takeoutOrderStatus);

	boolean addTakeOrderInfo(String to_id,Car car);

	List<BuyOrder> findAllBuyOrder(String u_id);

	BuyOrder findBuyOrder(String to_id,String u_id);

	List<String> findUserTakeOrder(String u_id);

	int findsaledCount(String sd_id);

	boolean updatesaledCount(String sd_id,int saledCount);
	
	TakeoutOrderStatus getOrderStatus(String tos_id);
	
	Boolean pay(String tos_id);

}
