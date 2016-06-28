package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.RestMenuType;

public interface RestMenuTypeDao {
	/**
	 * 根据商家id得到其菜单类别对象的集合
	 * @return
	 */
	List<RestMenuType> findAll(String condition);
	
	/**
	 * 插入一条商家菜单类别记录
	 * @param seller
	 * @return
	 */
	int add(RestMenuType rmt);
	
	/**
	 * 更新一条商家菜单类别记录
	 * @param seller
	 * @return
	 */
	int update(RestMenuType rmt);
	
	
	int getTotalCount(String condition);
	
	PagingBean<RestMenuType> getPageBean( int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
}
