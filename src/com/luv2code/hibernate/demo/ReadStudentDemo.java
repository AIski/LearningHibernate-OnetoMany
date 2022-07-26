package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            // use session object to save JAva object

            //create student object
            System.out.println("Creating new Student object");
            Student tempStudent = new Student("Donald", "Duck", "dduck@luv2code.com");

            //start a transaction
            System.out.println("Starting new transaction");
            session.beginTransaction();

            //save th student object
            System.out.println("Saving the student object.");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit the transaction
            System.out.println("Commiting the transaction.");
            session.getTransaction().commit();


            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve student basd on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());


            System.out.println("Get complete: " + myStudent);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
            System.out.println("Closing the factory.");
        }


    }
}
