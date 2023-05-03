package com.paresh.todolist;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class user_info {
	
	@Id
	private String user_id;
	private String user_password;
	
	public user_info(String user_id, String user_password) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
	}

	public user_info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.user_id + ":" + this.user_password;
	}
	

}
