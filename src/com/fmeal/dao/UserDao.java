package com.fmeal.dao;

import com.chdw.loc.domain.UserInfo;

public interface UserDao {
	UserInfo login(String userName,String userPass);

	boolean updateUser(UserInfo user);

	Boolean findUser(String username);

	UserInfo addUser(UserInfo userInfo);

	UserInfo updatePassword(UserInfo userInfo);

	Boolean setUserIcon(String u_id,String path);



}
