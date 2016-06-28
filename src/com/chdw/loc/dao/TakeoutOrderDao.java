package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.TakeoutOrder;

public interface TakeoutOrderDao {
	
	List<TakeoutOrder> findAll(String condition);
	
	int add(TakeoutOrder takeoutOrder);
	
	int update(TakeoutOrder takeoutOrder);
	
	int getTotalCount(String condition);
	
	PagingBean<TakeoutOrder> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
