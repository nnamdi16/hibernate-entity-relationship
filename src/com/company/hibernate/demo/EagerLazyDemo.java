package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
  
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
      
      //Create the bi-directional relationship between many courses and an  instructor.
      //Get the instructor from the db
      int theId = 1;
      Instructor tempInstructor = session.get(Instructor.class, theId);
      System.out.println("luv2Code: Instructor: " + tempInstructor);
      System.out.println("luv2Code: Courses: " + tempInstructor.getCourses());
  
  
      //Commit transaction
      session.getTransaction().commit();
  
      //Close the session
      session.close();
  
      //Get course for the instructor
      //Since the courses are lazy-loaded this should fail
      System.out.println("luv2Code: Courses: " + tempInstructor.getCourses());
    
      
      System.out.println("luv2Code: Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
