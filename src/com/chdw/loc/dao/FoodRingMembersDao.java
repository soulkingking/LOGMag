package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.FoodRingMembers;

public interface FoodRingMembersDao {
	
	/**
	 * 得到所有美食圈对象的集合
	 * @return
	 */
	List<FoodRingMembers> findAll(String condition);
	
	
	/**
	 * 插入一条美食圈记录
	 * @param seller
	 * @return
	 */
	int add(FoodRingMembers frm);
	
	/**
	 * 更新一条美食圈记录
	 * @param seller
	 * @return
	 */
	int update(FoodRingMembers frm);
	
	/**
	 * 得到美食圈对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	/**
	 * 获取所有的所有美食圈集合的分页对象
	 * @param pageSize
	 * @param currPage
	 * @param queryCondition
	 * @return
	 */
	PagingBean<FoodRingMembers> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
