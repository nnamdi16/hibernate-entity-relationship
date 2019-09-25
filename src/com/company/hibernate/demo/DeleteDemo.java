package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

            //Get instructor by the primary id or key
            int theId = 2;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found Instructor: " + tempInstructor);
            if (tempInstructor == null) {
                System.out.println("Instructor with the given Id  " + theId + " does not exist");

            } else {
                System.out.println("Deleted successfully ");
                //NB: It will also delete the details object because of the CascadeType.ALL
                session.delete(tempInstructor);
            }


            session.getTransaction().commit();
            System.out.println("Done !!");
        } finally {
            factory.close();
        }
    }
}
