package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {
  
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
      System.out.println(tempCourse);
      
      int studentId = 2;
      Student tempStudent = session.get(Student.class, studentId);
      System.out.println(tempStudent.getCourses());

//      Add student
      tempCourse.addStudent(tempStudent);
      session.save(tempCourse);

//      Course tempCourse = new Course("Talfa-How to score one million points");
//
//
//      //Save the course and leverage the cascade all
//      System.out.println("\n Saving the course");
//      session.save(tempCourse);
//      System.out.println("Saving the course  " + tempCourse);
//
//      //Create the student
//      Student tempStudent1 = new Student("John","Doe", "john@luv2code.com");
//      Student tempStudent2 = new Student("Mary","Eistein", "emary@luv2code.com");
//      Student tempStudent3 = new Student("Ka","Tan", "ktan@luv2code.com");
//      Student tempStudent4 = new Student("Bella","Solder", "bsolder@luv2code.com");
//      Student tempStudent5 = new Student("Nick","Robertson", "nrobertson@luv2code.com");
//
//      //Add students
//      tempCourse.addStudent(tempStudent1);
//      tempCourse.addStudent(tempStudent2);
//      tempCourse.addStudent(tempStudent3);
//      tempCourse.addStudent(tempStudent4);
//      tempCourse.addStudent(tempStudent5);
//
//      //Save students
//      System.out.println("\n Saving the students...");
//      session.save(tempStudent1);
//      session.save(tempStudent2);
//      session.save(tempStudent3);
//      session.save(tempStudent4);
//      session.save(tempStudent5);
//
//
      
      
      session.getTransaction().commit();
      System.out.println("Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
