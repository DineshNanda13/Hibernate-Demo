package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {

			//use the session object to save java object

			//create 3 student objects
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Jagdeep", "Sharma", "Jagga@gmail.com");
			Student tempStudent2 = new Student("Jaskaran", "Singh", "Jassi@gmail.com");
			Student tempStudent3 = new Student("Savy", "Gulati", "Savy@gmail.com");

			//start transaction
			session.beginTransaction();

			//save the student
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		}finally {
			factory.close();
		}

	}

}
