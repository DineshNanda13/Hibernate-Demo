package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display students
			displayStudents(theStudents);
			
			//last name query
			theStudents = session.createQuery("from Student s where s.lastName = 'sharma'").getResultList();
			
			//display students with last name of Sharma
			System.out.println("\nStudents who have last name of Sharma");
			displayStudents(theStudents);
			
			//OR Query
			theStudents = session.createQuery("from Student s where s.lastName = 'Kohli'"+ "OR s.firstName = 'Devika'").getResultList();
			
			//display students with last name of kohli and first name of devika
			System.out.println("\nStudents who have last name of kohli and first name of devika");
			displayStudents(theStudents);
			
			//LIKE Query
			theStudents = session.createQuery("from Student s where" + " s.email LIKE '%gmail.com'").getResultList();
			
			//display students with email ending with gmail.com
			System.out.println("\nStudents with email ending with gmail.com");
			displayStudents(theStudents);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudents: theStudents) {
			System.out.println(tempStudents);
		}
	}

}
