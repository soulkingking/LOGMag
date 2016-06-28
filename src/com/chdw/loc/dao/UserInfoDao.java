package com.chdw.loc.dao;

import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.domain.UserInfo;

public interface UserInfoDao {
	
	/**
	 * 得到所有用户信息对象
	 * @return
	 */
	List<UserInfo> findAll(String condition);
	
	/**
	 * 得到用户信息对象的总记录数
	 * @return
	 */
	int getTotalCount(String condition);
	
	/**
	 * 添加用户信息记录
	 * @param ui
	 * @return
	 */
	int add(UserInfo ui);
	
	/**
	 * 跟新用户信息记录
	 * @param ui
	 * @return
	 */
	int update(UserInfo ui);
	
	PagingBean<UserInfo> getPageBean(int pageSize, int currPage, String queryCondition);
	
	int delete(String IDs);
	
	public String validate(String username, String password);

}
