package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import com.company.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {
  
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
      
      //Get the course
//      int theId = 10;
//      Course tempCourse = session.get(Course.class, theId);
//      System.out.println("Working with the reviews " + tempCourse.getReviews());
      
      System.out.println("Delete a particular review ....");
      //Get the review
      int theReview = 6;
      Review tempReview = session.get(Review.class, theReview);
      System.out.println("Working with the Course: " + tempReview);
      session.delete(tempReview);
      session.getTransaction().commit();
      System.out.println("Done !!");
      
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
