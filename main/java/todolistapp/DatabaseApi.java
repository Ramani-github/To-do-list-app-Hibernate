package todolistapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class DatabaseApi {
	
	private HibernateTemplate hibernateTemplate;
	
	//check if userid is present
	
	public int check_userid(String name) {
		
		UserInfo usr_info = this.hibernateTemplate.get(UserInfo.class, name);
		
		if(usr_info == null)
			return 0;
		return 1;
		
	}
	
	//add a new account
	@Transactional
	public void add_new_account(String text, String passwordTyped) {
		
		UserInfo temp = new UserInfo();
		temp.setUser_id(text);
		temp.setUser_password(passwordTyped);
		
		List<TaskList> list = new ArrayList<TaskList>();
		temp.setTasks(list);
		this.hibernateTemplate.save(temp);
	}
	
	// get user password of a user

	public String get_userpassword(String usr_name) {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		return temp.getUser_password();
	}
	
	// change password of a user
	@Transactional
	public void change_password(String passwordTyped, String usr_name) {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		temp.setUser_password(passwordTyped);
		this.hibernateTemplate.update(temp); 
	}
	
	// get all tasks of a user

	public List<String> get_all_task(String usr_name) {
		
		List<String> result = new ArrayList<String>();
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		
		for(TaskList t:temp.getTasks())
			result.add(t.getTask_name());
		
		return result;
	}
	
	// remove user and all tasks
	@Transactional
	public void remove_user(String usr_name) {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		
		this.hibernateTemplate.delete(temp);
	}
	
	// get all details of a task
	
	public List<String> get_task_details(String usr_name, String task) {
		
		List<String> result = new ArrayList<String>();
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		List<TaskList> tasks_list = temp.getTasks();
		
		int i=0;
		
		for(;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(task))
				break;
			
		}
		
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		result.add( tasks_list.get(i).getTask_details() );
		result.add( formatDate.format(tasks_list.get(i).getStart_date()) );
		result.add( formatTime.format(tasks_list.get(i).getStart_time()) );
		result.add( formatDate.format(tasks_list.get(i).getEnd_date()) );
		result.add( formatTime.format(tasks_list.get(i).getEnd_time()) );
		
		return result;
	}
	
	//delete a task
	@Transactional
	public void delete_task(String usr_name, String task) {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		int i=0;
		
		for(;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(task))
				break;
			
		}
		
		temp.remove_task(i);
		
		this.hibernateTemplate.update(temp);
	}
	
	//check if a task exist

	public int check_task_exist(String usr_name, String new_task) {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		List<TaskList> tasks_list = temp.getTasks();
		
		int ans = 0;
		
		for(int i=0;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(new_task))
			{
				ans = 1;
				break;
			}	
		}
			
		return ans;
	}
	
	// add a new task
	@Transactional
	public void add_task(String usr_name, String task_name,String task_details, String start_date, 
			String start_time, String end_date, String end_time) throws ParseException {
		
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		
		TaskList task = new TaskList();
		
		task.setTask_name(task_name);
		task.setTask_details(task_details);
		task.setStart_date(new SimpleDateFormat("dd-MM-yyyy").parse(start_date));
		task.setStart_time(new SimpleDateFormat("HH:mm").parse(start_time));
		task.setEnd_date(new SimpleDateFormat("dd-MM-yyyy").parse(end_date));
		task.setEnd_time(new SimpleDateFormat("HH:mm").parse(end_time));
		task.setUser_info(temp);
		
		temp.add_task(task);
		
		this.hibernateTemplate.update(temp);
	}
	
	// update a task
	@Transactional
	public void update_task(String usr_name, String new_task, String task_details, String start_date, 
			String start_time, String end_date, String end_time, String old_task) throws ParseException {
				
		UserInfo temp = this.hibernateTemplate.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		int i=0;
		
		for(;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(old_task))
				break;
			
		}
		
		temp.update_task(i, new_task, task_details, new SimpleDateFormat("dd-MM-yyyy").parse(start_date),
				new SimpleDateFormat("HH:mm").parse(start_time), new SimpleDateFormat("dd-MM-yyyy").parse(end_date),
						new SimpleDateFormat("HH:mm").parse(end_time));
		
		this.hibernateTemplate.update(temp);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
