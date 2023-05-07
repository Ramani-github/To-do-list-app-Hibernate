package todolistapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
        session = factory.openSession();
	}
	
	//check if userid is present
	
	public int check_userid(String name) {
		
		UserInfo usr_info = (UserInfo)session.get(UserInfo.class, name);
		
		if(usr_info == null)
			return 0;
		return 1;
		
	}
	
	//add a new account

	public void add_new_account(String text, String passwordTyped) {
		
		UserInfo temp = new UserInfo();
		temp.setUser_id(text);
		temp.setUser_password(passwordTyped);
		
		List<TaskList> list = new ArrayList<TaskList>();
		temp.setTasks(list);
		
		tr = session.beginTransaction();
		session.save(temp);
		tr.commit();
	}
	
	// get user password of a user

	public String get_userpassword(String usr_name) {
		
		String query = "from UserInfo where user_id=:x";
		Query<?> q = session.createQuery(query);
		q.setParameter("x", usr_name);
		UserInfo temp = (UserInfo) q.uniqueResult();
		return temp.getUser_password();
	}
	
	// change password of a user

	public void change_password(String passwordTyped, String usr_name) {
		String query = "update UserInfo set user_password=:p where user_id=:x";
		Query<?> q = session.createQuery(query);
		q.setParameter("p", passwordTyped);
		q.setParameter("x", usr_name);
		tr.commit();
	}
	
	// get all tasks of a user

	public List<String> get_all_task(String usr_name) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		for(TaskList t:temp.getTasks())
			result.add(t.getTask_name());
		
		return result;
	}
	
	// remove user

	public void remove_user_userinfo(String usr_name) {
		// TODO Auto-generated method stub
		
	}
	
	// delete all tasks

	public void delete_alltasks(String usr_name) {
		// TODO Auto-generated method stub
		
	}
	
	// get all details of a task
	
	public List<String> get_task_details(String usr_name, String task) {
		
		List<String> result = new ArrayList<String>();
		String query = "select t.task_details, t.start_date, t.start_time, t.end_date, t.end_time, u.user_id "
				+ "from TaskList as t INNER JOIN t.user_info as u where u.user_id=:x and t.task_name=:y";
		
		Query<?> q = session.createQuery(query);
		
		q.setParameter("x", usr_name);
		q.setParameter("y", task);
		
		@SuppressWarnings("unchecked")
		List<Object []> temp = (List<Object []>) q.getResultList();
				
		//SimpleDateFormat formatTime = new SimpleDateFormat("HH.mm");
		//SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		result.add(  ((temp.get(0))[0]).toString()  );
		result.add( ((temp.get(0))[1]).toString() );
		result.add( ((temp.get(0))[2]).toString() );
		result.add( ((temp.get(0))[3]).toString() );
		result.add( ((temp.get(0))[4]).toString() );
		
		return result;
	}
	
	//delete a task

	public void delete_task(String usr_name, String task) {
		// TODO Auto-generated method stub
	}
	
	//check if a task exist

	public int check_task_exist(String usr_name, String new_task) {
		
		String query = "select t.task_name, u.user_id from TaskList as t "
				+ "INNER JOIN t.user_info as u where u.user_id=:x and t.task_name=:y";
		
		Query<?> q = session.createQuery(query);
		
		q.setParameter("x", usr_name);
		q.setParameter("y", new_task);
		
		@SuppressWarnings("unchecked")
		List<Object []> temp = (List<Object []>) q.getResultList();
		
		if(temp.size()== 0)
			return 0;
		return 1;
	}
	
	// add a new task

	public void add_task(String usr_name, String task_name,String task_details, String start_date, 
			String start_time, String end_date, String end_time) throws ParseException {
		
		UserInfo temp = (UserInfo)session.get(UserInfo.class, usr_name);
		
		List<TaskList> tasks_list = temp.getTasks();
		
		temp.setTasks(null);
		
		TaskList task = new TaskList();
		
		task.setTask_name(task_name);
		task.setTask_details(task_details);
		task.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse(start_date));
		task.setStart_time(new SimpleDateFormat("HH:mm").parse(start_time));
		task.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
		task.setEnd_time(new SimpleDateFormat("HH:mm").parse(end_time));
		task.setUser_info(temp);
		
		tasks_list.add(task);
		
		temp.setTasks(tasks_list);
		
		tr.commit();
		
	}
	
	// update a task

	public void update_task(String usr_name, String new_task, String task_details, String start_date, 
			String start_time, String end_date, String end_time, String old_task) throws ParseException {
		
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
		task.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse(start_date));
		task.setStart_time(new SimpleDateFormat("HH:mm").parse(start_time));
		task.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
		task.setEnd_time(new SimpleDateFormat("HH:mm").parse(end_time));
		task.setUser_info(temp);
		
		tasks_list.add(task);
		
		temp.setTasks(tasks_list);
		
		tr.commit();
		
	}

}
