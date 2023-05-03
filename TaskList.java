package com.paresh.todolist;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskList {
	
	@Id
	private int num;
	
	private String user_name;
	private String task_name;
	private String task_details;
	private String start_date;
	private String end_date;
	private String start_time;
	private String end_time;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	
	
	public String getTask_details() {
		return task_details;
	}
	public void setTask_details(String task_details) {
		this.task_details = task_details;
	}
	
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public TaskList(int num, String user_name, String task_name, String task_details, String start_date, String end_date,
			String start_time, String end_time) {
		super();
		this.num = num;
		this.user_name = user_name;
		this.task_name = task_name;
		this.task_details = task_details;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaskList [num=" + num + ", task_name=" + task_name 
				+ ", task_details=" + task_details + ", start_date="
				+ start_date + ", end_date=" + end_date + ", start_time=" 
				+ start_time + ", end_time=" + end_time + ", end_time=" + user_name + "]";
	}
	
	

}
