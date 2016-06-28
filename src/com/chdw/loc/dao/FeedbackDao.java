package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.Feedback;

public interface FeedbackDao {
	
	
	/**
	 * 根据反馈id得到对应反馈对象
	 */
	List<Feedback> findAll(String condition);
	
	/**
	 * 插入一条反馈内容
	 * @return
	 */
	int add(Feedback fb);
	
	/**
	 * 得到所有反馈信息的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	PagingBean<Feedback> getPageBean(int pageSize, int currPage, String queryCondition);
	
	/**
	 * 删除一条或多条记录
	 * @param IDS
	 * @return
	 */
	int delete(String IDs);
}
