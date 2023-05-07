# TaskBox a To-do list app in Java with MySQL

A To-Do List app using java. User can create account, login, add tasks, change or delete tasks, change password and delete account. It connects to app database (MySQL) using hibernate.Hibernate is a Java framework that simplifies the development of Java application to interact with the database. It is an open source, lightweight, ORM (Object Relational Mapping) tool. Hibernate implements the specifications of JPA (Java Persistence API) for data persistence. I have used mavenin this poject.Maven is a powerful project management tool that is based on POM (project object model). It is used for projects build, dependency etc.

## Required Packages
* JDK
* MySQL

## IDE Used
* Eclipse

## How to run

### Ubuntu

```
* Install mysql-server : sudo apt-get install mysql-server
* first export the jar file - export CLASSPATH="/path/to/file":${CLASSPATH}
* In Main.java. Change mysql_username and mysql_password to your url, username and password.
* Compile - javac Main.java
* Run - java Main
```

* You dont need to create any database or tables as database and tables will created by app. But if you want to create initial user_info tables to fill some values follow this steps:

```
* Start mysql : sudo mysql -u root -p ( password - root)
* To create database : CREATE DATABASE to_do_list_app;
* Connect to database : connect to_do_list_app;
* Create user_info table : CREATE TABLE user_info( user_id varchar(20), user_password varchar(20), PRIMARY KEY (user_id));
* Now that table is created, you can fill some values which you want initially using mySQL commands.Same can also be done by using GUI.
```

## Images

* Login page

![Login](home.PNG)

* Register

![Register](create_Account.PNG)

* View and add Task

![Register](Task.PNG)
