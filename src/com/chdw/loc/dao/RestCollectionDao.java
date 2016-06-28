package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.RestCollection;

public interface RestCollectionDao {
	
	/**
	 * 得到所有商家对象的集合
	 * @return
	 */
	List<RestCollection> findAll(String condition);
	
	/**
	 * 插入一条商家记录
	 * @param seller
	 * @return
	 */
	int add(RestCollection sc);
	
	/**
	 * 更新一条商家记录
	 * @param seller
	 * @return
	 */
	int update(RestCollection sc);
	
	/**
	 * 得到商家对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	PagingBean<RestCollection> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
