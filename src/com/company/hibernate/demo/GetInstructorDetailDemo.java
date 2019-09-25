package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {
        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {
            //Use the session object to save Java Object


            //Start a transaction
            session.beginTransaction();
            //Get the instructor detail
            int theId = 1;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            //Print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            //Print the associated Instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());


            session.getTransaction().commit();
            System.out.println("Done !!");
        } finally {
            factory.close();
        }
    }
}
