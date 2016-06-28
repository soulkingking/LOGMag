package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SellerDish;

public interface SellerDishDao {
	
	/**
	 * 得到其菜单类别对应的菜肴对象的集合
	 * @return
	 */
	List<SellerDish> findAll(String condition);
	
	/**
	 * 插入一条商家菜肴记录
	 * @param seller
	 * @return
	 */
	int add(SellerDish sd);
	
	/**
	 * 更新一条商家菜肴记录
	 * @param seller
	 * @return
	 */
	int update(SellerDish sd);
	
	
	int getTotalCount(String condition);
	
	PagingBean<SellerDish> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
