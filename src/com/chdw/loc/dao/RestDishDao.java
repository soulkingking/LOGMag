package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.RestDish;

public interface RestDishDao {

	/**
	 * 根据餐厅菜单类别id得到其菜单类别对应的菜肴对象的集合
	 * @return
	 */
	List<RestDish> findAll(String condition);
	
	/**
	 * 插入一条餐厅菜肴记录
	 * @param seller
	 * @return
	 */
	int add(RestDish rd);
	
	/**
	 * 更新一条餐厅菜肴记录
	 * @param seller
	 * @return
	 */
	int update(RestDish rd);
	
	int getTotalCount(String condition);
	
	PagingBean<RestDish> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
	
}
