package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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

            /**
             //Create the objects
             Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
             InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv 2 code");
             **/

            //Create the objects
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.duplex.com/youtube", "luv 2 code Java");
            Instructor tempInstructor = new Instructor("Salmon", "Duplex", "duplex@luv2code.com");
            

            //Associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //Start a transaction
            session.beginTransaction();

            //Save the instructor
            //NB: This will also save the detail object because of CascadeType.ALL, save the objects and any associated object.
            System.out.println("Saving Instructor " + tempInstructor);
            session.save(tempInstructor);
            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done !!");
        } finally {
            factory.close();
        }
    }
}
