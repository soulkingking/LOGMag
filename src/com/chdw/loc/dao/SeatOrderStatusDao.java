package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SeatOrderStatus;

public interface SeatOrderStatusDao {
	
	/**
	 * 得到订单状态信息
	 * @return
	 */
	List<SeatOrderStatus> findAll(String condition);
	
	/**
	 * 插入一条订单状态记录
	 * @param seller
	 * @return
	 */
	int add(SeatOrderStatus sos);
	
	/**
	 * 更新一条订单状态记录
	 * @param seller
	 * @return
	 */
	int update(SeatOrderStatus sos);
	
    int getTotalCount(String condition);
		
	PagingBean<SeatOrderStatus> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);


}
