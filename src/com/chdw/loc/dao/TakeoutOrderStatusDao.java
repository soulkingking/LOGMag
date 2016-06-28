package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.TakeoutOrderStatus;

public interface TakeoutOrderStatusDao {
	
	List<TakeoutOrderStatus> findAll(String condition);
	
	int add(TakeoutOrderStatus takeos);
	
	int update(TakeoutOrderStatus takeos);
	
	int getTotalCount(String condition);
	
	PagingBean<TakeoutOrderStatus> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
