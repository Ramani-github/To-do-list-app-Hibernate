package todolistapp;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User_Info")
public class UserInfo {
	
	@Id
	@Column(length=20,name="User_Id")
	private String user_id;
	
	@Column(length=20,name="User_Password")
	private String user_password;
	
	@OneToMany(mappedBy="user_info",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List<TaskList> tasks;
	

	public UserInfo(String user_id, String user_password, List<TaskList> tasks) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.tasks = tasks;
	}

	public List<TaskList> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskList> tasks) {
		this.tasks = tasks;
	}

	public UserInfo() {
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
	
	// add task
	
	public void add_task(TaskList task)
	{
		this.tasks.add(task);
	}
	
	// remove task
	public void remove_task(int i)
	{
		this.tasks.remove(i);
	}
	
	// update task
	public void update_task(int i, String new_task, String task_details, Date start_date, Date start_time, 
			Date end_date, Date end_time) {
		this.tasks.get(i).setTask_name(new_task);
		this.tasks.get(i).setTask_details(task_details);
		this.tasks.get(i).setStart_date(start_date);
		this.tasks.get(i).setStart_time(start_time);
		this.tasks.get(i).setEnd_date(end_date);
		this.tasks.get(i).setEnd_time(end_time);
	}
	

}
