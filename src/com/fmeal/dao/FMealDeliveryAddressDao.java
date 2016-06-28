package com.fmeal.dao;

import java.util.List;

import com.chdw.loc.domain.DeliveryAddress;

public interface FMealDeliveryAddressDao {
	List<DeliveryAddress> findUserAddress(String u_id);

	List<DeliveryAddress> addDeliveryAddress(DeliveryAddress deliveryAddress);

	List<DeliveryAddress> deleteUserAddress(String da_id,String u_id);
	
	List<DeliveryAddress> updateDeliveryAddress(DeliveryAddress deliveryAddress);

}
