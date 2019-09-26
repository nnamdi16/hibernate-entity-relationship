package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import com.company.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {
  
  public static void main(String[] args) {
    //Create session factory
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();
    
    //Create session
    Session session = factory.getCurrentSession();
    
    try {
      //Use the session object to save Java Object
      
      //Start a transaction
      session.beginTransaction();
      
      //Create a course
      Course tempCourse = new Course("Pacman-How to score one million points");
      
      //Add some review
      tempCourse.addReview(new Review("Great course...Love it !!! "));
      tempCourse.addReview(new Review("Great course...Love it !!! "));
      tempCourse.addReview(new Review("Job well done!!! "));
      tempCourse.addReview(new Review("Dumb Coure !!! "));
      tempCourse.addReview(new Review("Excellent !!! "));
      tempCourse.addReview(new Review("Keep it up !!! "));
      tempCourse.addReview(new Review("Couldn't finish it !!! "));
      
      
      //Save the course and leverage the cascade all
      System.out.println("Saving the course");
      System.out.println(tempCourse);
      System.out.println(tempCourse.getReviews());
      session.save(tempCourse);
      
      
      session.getTransaction().commit();
      System.out.println("Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
