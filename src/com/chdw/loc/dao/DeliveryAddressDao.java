package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.DeliveryAddress;

public interface DeliveryAddressDao {
	
	/**
	 * 增加送餐地址
	 * @param da
	 * @return
	 */
	int add(DeliveryAddress da);
	
	/**
	 * 更新送餐地址
	 * @param da
	 * @return
	 */
	int update(DeliveryAddress da);
	
	/**
	 * 根据条件查询到所有送餐地址对象
	 * @param condition
	 * @return
	 */
	List<DeliveryAddress> findAll(String condition);
	
	
	int getTotalCount(String condition);
	
	PagingBean<DeliveryAddress> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
