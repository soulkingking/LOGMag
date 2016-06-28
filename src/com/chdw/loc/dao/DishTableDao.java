package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.DishTable;

public interface DishTableDao {
	
	int add(DishTable dt);
	
	int update(DishTable dt);
	
	List<DishTable> findAll(String condition);
	
	int getTotalCount(String condition);
	
	PagingBean<DishTable> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
