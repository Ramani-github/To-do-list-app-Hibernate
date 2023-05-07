package todolistapp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Tasks_List")
public class TaskList {
	
	@Id
	@Column(length=20,name="Task_Name")
	private String task_name;
	
	@Column(length=20,name="Task_Details")
	private String task_details;
	
	@Column(name="Start_Date")
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Column(name="End_Date")
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
	@Column(name="Start_Time")
	@Temporal(TemporalType.TIME)
	private Date start_time;
	
	@Column(name="End_Time")
	@Temporal(TemporalType.TIME)
	private Date end_time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo user_info;
	
	public UserInfo getUser_info() {
		return user_info;
	}
	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
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
	
	
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public TaskList(String task_name, String task_details, Date start_date, Date end_date, Date start_time,
			Date end_time, UserInfo user_info) {
		super();
		this.task_name = task_name;
		this.task_details = task_details;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.user_info = user_info;
	}
	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaskList [task_name=" + task_name + ", task_details=" + task_details + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", start_time=" + start_time + ", end_time=" + end_time + ", user_info="
				+ user_info + "]";
	}
	
	

}
