package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {
  
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
      System.out.println("Instructor: " + tempInstructor);
      
      //Get course for the instructor
      System.out.println("Courses: " + tempInstructor.getCourses());
  
      //Bi-directional implementation using a course Id to access the instructor.
      int courseId = 11;
      Course tempCourse = session.get(Course.class, courseId);
      System.out.println("Get the course " + tempCourse);
  
      //Use the course to access the instructor
      System.out.println("Instructor Info : " + tempCourse.getInstructor());
      session.getTransaction().commit();
      System.out.println("Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
