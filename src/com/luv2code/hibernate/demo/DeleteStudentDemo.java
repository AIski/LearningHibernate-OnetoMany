package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;
            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // First way of deleting:
//            Student myStudent= session.get(Student.class, studentId);
//            System.out.println("Deleting Student: "+myStudent);
//            session.delete(myStudent);
            // Second way of deleting:
            session.
                     createQuery("delete from Student where id=2")
                    .executeUpdate();

            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
            System.out.println("Closing the factory.");
        }


    }
}
