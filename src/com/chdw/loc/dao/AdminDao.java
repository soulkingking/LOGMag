package com.chdw.loc.dao;

import com.chdw.loc.domain.Admin;

public interface AdminDao {
	
	/**
	 * 判断该用户名是否已存在
	 * @param username
	 * @return
	 */
	Admin find(String username);
	
	/**
	 * 验证用户登录信息
	 * @param inputUname
	 * @param inputPwd
	 * @return
	 */
	int validate(String inputUname, String inputPwd);
	
	/**
	 * 插入一条用户验证信息，注册用户
	 * @param username
	 * @param password
	 * @return
	 */
	int add(String username, String password);
	
	/**
	 * 更新一条验证记录
	 * @param seller
	 * @return
	 */
	int update(Admin uv);

}
