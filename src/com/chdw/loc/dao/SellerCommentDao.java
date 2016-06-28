package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.SellerComment;

public interface SellerCommentDao {
	
	
	/**
	 * 得到所有商家对象的集合
	 * @return
	 */
	List<SellerComment> findAll(String condition);
	
	/**
	 * 插入一条积分记录
	 * 
	 * @param seller
	 * @return
	 */
	int add(SellerComment sc);

	/**
	 * 更新一条积分记录
	 * 
	 * @param seller
	 * @return
	 */
	int update(SellerComment sc);
	
	int getTotalCount(String condition);

	/**
	 * 得到pagingBean对象
	 * 
	 * @param pageSize
	 * @param currPage
	 * @param queryCondition
	 * @return
	 */
	PagingBean<SellerComment> getPageBean(int pageSize, int currPage,
			String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
}
