package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SellerMenuType;

public interface SellerMenuTypeDao {
	
	/**
	 * 根据商家id得到其菜单类别对象的集合
	 * @return
	 */
	List<SellerMenuType> findAll(String condition);
	
	/**
	 * 插入一条商家菜单类别记录
	 * @param seller
	 * @return
	 */
	int add(SellerMenuType smt);
	
	/**
	 * 更新一条商家菜单类别记录
	 * @param seller
	 * @return
	 */
	int update(SellerMenuType smt);
	
	int getTotalCount(String condition);
	
	PagingBean<SellerMenuType> getPageBean(int pageSize, int currPage, String queryCondition);
	
	int delete(String IDs);
	
	
}
