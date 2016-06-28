package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.Restaurant;

public interface RestaurantDao {
	
	/**
	 * 得到所有餐厅对象的集合
	 * @return
	 */
	List<Restaurant> findAll(String condition);
	
	/**
	 * 插入一条商家记录
	 * @param seller
	 * @return
	 */
	int add(Restaurant rest);
	
	/**
	 * 更新一条商家记录
	 * @param seller
	 * @return
	 */
	int update(Restaurant rest);
	
	/**
	 * 得到餐厅对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	PagingBean<Restaurant> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);


}
