package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseManyToManyDemo {
  
  public static void main(String[] args) {
    //Create session factory
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();
    
    //Create session
    Session session = factory.getCurrentSession();
    
    try {
      //Use the session object to save Java Object
      
      //Start a transaction
      session.beginTransaction();
      
      //Create a course
      int theId = 14;
      Course tempCourse = session.get(Course.class, theId);
      System.out.println("Deleting  " + tempCourse);
      
      
      session.delete(tempCourse);
      
      
      session.getTransaction().commit();
      System.out.println("Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
