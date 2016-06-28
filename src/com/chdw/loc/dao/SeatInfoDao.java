package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SeatInfo;

public interface SeatInfoDao {


	/**
	 * 得到所有订座信息
	 * @param condition
	 * @return
	 */
	List<SeatInfo> findAll(String condition);
	
	/**
	 * 插入订座信息
	 * @param id
	 * @return
	 */
	int add(SeatInfo si);
	
	/**
	 * 更新订座信息
	 * @param si
	 * @return
	 */
	int update(SeatInfo si);
	
	/**
	 * 得到所有订座信息的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	PagingBean<SeatInfo> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
}
