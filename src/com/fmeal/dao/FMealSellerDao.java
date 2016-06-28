package com.fmeal.dao;

import java.util.List;

import com.chdw.loc.domain.SellerData;
import com.chdw.loc.domain.SellerDishData;

public interface FMealSellerDao {
	SellerData findSellerData(String s_id);
	
	List<SellerDishData> findSellerDishData(String s_id);

}
