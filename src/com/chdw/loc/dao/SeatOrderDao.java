package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SeatOrder;

public interface SeatOrderDao {
	
	/**
	 * @param condition
	 * @return
	 */
	List<SeatOrder> findAll(String condition);
	
	/**
	 * 添加订座订单
	 * @param so
	 * @return
	 */
	int add(SeatOrder so);
	
	/**
	 * 更新订座订单
	 * @param so
	 * @return
	 */
	int update(SeatOrder so);
	
    int getTotalCount(String condition);
	
	PagingBean<SeatOrder> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
}
