package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Course;
import com.company.hibernate.demo.entity.Instructor;
import com.company.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            //Create some courses
            Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
            Course tempCourse2 = new Course("The pinball Masterclass");
            Course tempCourse3 = new Course("Fashion out your own learning");

            //Add courses to the Instructor

            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            tempInstructor.add(tempCourse3);

            //Save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);

            session.getTransaction().commit();
            System.out.println("Done !!");
        } finally {
            //Add clean up code.
            session.close();
            factory.close();
        }
    }
}
