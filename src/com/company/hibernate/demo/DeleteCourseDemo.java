package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
  
  public static void main(String[] args) {
    //Create session factory
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();
    
    //Create session
    Session session = factory.getCurrentSession();
    
    try {
      //Use the session object to save Java Object
      
      //Start a transaction
      session.beginTransaction();
      
      //Get course
      int theId = 10;
      Course tempCourse = session.get(Course.class, theId);
      
      //Delete course
      System.out.println("Delete course: " + tempCourse);
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
