package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.Remark;

public interface RemarkDao {
	
	/**
	 * 得到所有美食圈 成员备注对象的集合
	 * @return
	 */
	List<Remark> findAll(String condition);
	
	
	/**
	 * 插入一条美食圈 成员备注记录
	 * @param seller
	 * @return
	 */
	int add(Remark frmRemark);
	
	/**
	 * 更新一条美食圈 成员备注记录
	 * @param seller
	 * @return
	 */
	int update(Remark frmRemark);
	
	/**
	 * 得到美食圈 成员备注对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	/**
	 * 获取所有的所有美食圈 成员备注的分页对象
	 * @param pageSize
	 * @param currPage
	 * @param queryCondition
	 * @return
	 */
	PagingBean<Remark> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);

}
