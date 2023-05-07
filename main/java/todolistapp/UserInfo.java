package todolistapp;

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
	
	@OneToMany(mappedBy="user_info",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
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
	

}
