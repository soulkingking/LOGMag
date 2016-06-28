package com.fmeal.dao;


import java.util.List;

import com.chdw.loc.domain.Seller;
import com.chdw.loc.domain.SellerCollection;

public interface FMealCollectionDao {
	SellerCollection findCollection(String s_id,String u_id);

	SellerCollection addCollection(String s_id,String u_id);

	SellerCollection deleteCollection(String s_id,String u_id);

	List<Seller> findUserCollection(String u_id);

}
