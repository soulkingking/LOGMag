package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.Seller;

public interface SellerDao {
	
	/**
	 * 得到所有商家对象的集合
	 * @return
	 */
	List<Seller> findAll(String condition);
	
	/**
	 * 插入一条商家记录
	 * @param seller
	 * @return
	 */
	int add(Seller seller);
	
	/**
	 * 更新一条商家记录
	 * @param seller
	 * @return
	 */
	int update(Seller seller);
	
	/**
	 * 得到商家对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	PagingBean<Seller> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);


}
