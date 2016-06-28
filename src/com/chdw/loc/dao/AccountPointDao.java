package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.AccountPoint;

public interface AccountPointDao {
	
	/**
	 * 得到所有商家对象的集合
	 * @return
	 */
	List<AccountPoint> findAll(String condition);

	/**
	 * 插入一条积分记录
	 * 
	 * @param seller
	 * @return
	 */
	int add(AccountPoint ap);

	/**
	 * 更新一条积分记录
	 * 
	 * @param seller
	 * @return
	 */
	int update(AccountPoint ap);
	
	int getTotalCount(String condition);

	/**
	 * 得到pagingBean对象
	 * 
	 * @param pageSize
	 * @param currPage
	 * @param queryCondition
	 * @return
	 */
	PagingBean<AccountPoint> getPageBean(int pageSize, int currPage,
			String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
