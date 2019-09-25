package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
  
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
  
      //Option 2: Hibernate query with HQL
      
      //Create the bi-directional relationship between many courses and an  instructor.
      //Get the instructor from the db
      int theId = 1;
      Query<Instructor> query = session.createQuery("select i from Instructor i " +
                      "JOIN FETCH i.courses" +
                      " where i.id =:theInstructorId "
              , Instructor.class);
  
      //Set parameter on the query
      query.setParameter("theInstructorId", theId);
  
      //execute query and get instructor
      Instructor tempInstructor = query.getSingleResult();
      
      System.out.println("luv2Code: Instructor: " + tempInstructor);
  
  
      //Commit transaction
      session.getTransaction().commit();
      
      //Close the session
      session.close();
      
      System.out.println("\n luv2code: The session is now closed ! \n");
      
      
      //Get course for the instructor
      //Since the courses are lazy-loaded this should fail. It won't fail only if the courses were loaded before while the session was open.
      System.out.println("luv2Code: Courses: " + tempInstructor.getCourses());
      
      
      System.out.println("luv2Code: Done !!");
    } finally {
      //Add clean up code.
      session.close();
      factory.close();
    }
  }
}
