package com.chdw.loc.domain;

public class Admin {
	
	private String password;	//密码
	private String username;	//用户名
	private String valid_id;	//id
	
	public Admin() {
	}
	
	public Admin(String valid_id, String username, String password) {
		this.valid_id = valid_id;
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public String getValid_id() {
		return valid_id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setValid_id(String valid_id) {
		this.valid_id = valid_id;
	}

}
