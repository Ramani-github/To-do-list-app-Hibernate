package com.paresh.todolist;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Configuration cfg = new Configuration();
        cfg.configure("hibernatecfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        
        user_info ui = new user_info();
        ui.setUser_id("paresh");
        ui.setUser_password("pari");
        
        TaskList tasks = new TaskList();
        tasks.setNum(0);
        tasks.setTask_name("study");
        tasks.setTask_details("java");
        tasks.setStart_time("21:00");
        tasks.setStart_date("02-09-2023");
        tasks.setEnd_date("02-09-2024");
        tasks.setEnd_time("21:0");
        
        System.out.println(ui);
        System.out.println(tasks);
        
        Session session = factory.openSession();
        
        Session session2 = factory.openSession();
        
        
        Transaction tr = session.beginTransaction();
        
        Transaction tr2 = session2.beginTransaction();
        
        session.save(ui);
        
        tr.commit();
        
        session2.save(tasks);
        
        tr2.commit();
        
        session.close();
        session2.close();
    }
}
