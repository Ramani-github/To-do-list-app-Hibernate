package todolistapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseApi {
	
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;
	private Transaction tr;
	
	DatabaseApi()
	{
		cfg = new Configuration();
        cfg.configure("hibernatecfg.xml");
        factory = cfg.buildSessionFactory();
	}
	
	//check if userid is present
	
	public int check_userid(String name) {
		
		session = factory.openSession();
		UserInfo usr_info = (UserInfo)session.get(UserInfo.class, name);
		session.close();
		
		if(usr_info == null)
			return 0;
		return 1;
		
	}
	
	//add a new account

	public void add_new_account(String text, String passwordTyped) {
		
		session = factory.openSession();
		UserInfo temp = new UserInfo();
		temp.setUser_id(text);
		temp.setUser_password(passwordTyped);
		
		List<TaskList> list = new ArrayList<TaskList>();
		temp.setTasks(list);
		tr = session.beginTransaction();
		session.save(temp);
		tr.commit();
		session.close();
	}
	
	// get user password of a user

	public String get_userpassword(String usr_name) {
		session = factory.openSession();
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		tr = session.beginTransaction();
		session.close();
		return temp.getUser_password();
	}
	
	// change password of a user

	public void change_password(String passwordTyped, String usr_name) {
		session = factory.openSession();
		tr = session.beginTransaction();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		temp.setUser_password(passwordTyped);
        session.update(temp); 
		tr.commit();
		session.close();
	}
	
	// get all tasks of a user

	public List<String> get_all_task(String usr_name) {
		
		List<String> result = new ArrayList<String>();
		
		session = factory.openSession();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		for(TaskList t:temp.getTasks())
			result.add(t.getTask_name());
		
		session.close();
		
		return result;
	}
	
	// remove user and all tasks

	public void remove_user(String usr_name) {
		
		session = factory.openSession();
		tr = session.beginTransaction();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		session.delete(temp);
		
		tr.commit();
		
		session.close();
		
	}
	
	// get all details of a task
	
	public List<String> get_task_details(String usr_name, String task) {
		
		List<String> result = new ArrayList<String>();
		
		session = factory.openSession();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
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
		
		session.close();
		
		return result;
	}
	
	//delete a task

	public void delete_task(String usr_name, String task) {
		
		session = factory.openSession();
		tr = session.beginTransaction();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		temp.setTasks(null);
		
		int i=0;
		
		for(;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(task))
				break;
			
		}
		
		tasks_list.remove(i);
		
		session.update(temp);
		tr.commit();
		
		session.close();
		
	}
	
	//check if a task exist

	public int check_task_exist(String usr_name, String new_task) {
		
		session = factory.openSession();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		List<TaskList> tasks_list = temp.getTasks();
		
		int ans = 0;
		
		for(int i=0;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(new_task))
			{
				ans = 1;
				break;
			}	
		}
		
		session.close();
		
		return ans;
	}
	
	// add a new task

	public void add_task(String usr_name, String task_name,String task_details, String start_date, 
			String start_time, String end_date, String end_time) throws ParseException {
		
		session = factory.openSession();
		tr = session.beginTransaction();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		temp.setTasks(null);
		
		TaskList task = new TaskList();
		
		task.setTask_name(task_name);
		task.setTask_details(task_details);
		task.setStart_date(new SimpleDateFormat("dd-MM-yyyy").parse(start_date));
		task.setStart_time(new SimpleDateFormat("HH:mm").parse(start_time));
		task.setEnd_date(new SimpleDateFormat("dd-MM-yyyy").parse(end_date));
		task.setEnd_time(new SimpleDateFormat("HH:mm").parse(end_time));
		task.setUser_info(temp);
		
		tasks_list.add(task);
		
		temp.setTasks(tasks_list);
		session.update(temp);
		
		tr.commit();
		session.close();
		
	}
	
	// update a task

	public void update_task(String usr_name, String new_task, String task_details, String start_date, 
			String start_time, String end_date, String end_time, String old_task) throws ParseException {
		
		session = factory.openSession();
		tr = session.beginTransaction();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		temp.setTasks(null);
		
		int i=0;
		
		for(;i<tasks_list.size();i++) {
			
			if(tasks_list.get(i).getTask_name().equals(old_task))
				break;
			
		}
		
		tasks_list.remove(i);
		
		TaskList task = new TaskList();
		
		task.setTask_name(new_task);
		task.setTask_details(task_details);
		task.setStart_date(new SimpleDateFormat("dd-MM-yyyy").parse(start_date));
		task.setStart_time(new SimpleDateFormat("HH:mm").parse(start_time));
		task.setEnd_date(new SimpleDateFormat("dd-MM-yyyy").parse(end_date));
		task.setEnd_time(new SimpleDateFormat("HH:mm").parse(end_time));
		task.setUser_info(temp);
		
		tasks_list.add(task);
		
		temp.setTasks(tasks_list);
		session.update(temp);
		
		tr.commit();
		session.close();
		
	}

}
