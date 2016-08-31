package com.jenius.web.meta;

public class Person {
	private int id;
	private String userName;
	private String password;
	private String nickName;
	private int usertype;
	private boolean isLogin = false;
	
	
	
	


	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int userType) {
		this.usertype = userType;
	}
	
}
