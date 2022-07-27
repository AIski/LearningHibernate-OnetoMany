package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {

        // create session factory
SessionFactory factory= new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Student.class)
        .buildSessionFactory();

        //create a session
        Session session= factory.getCurrentSession();

                try{


                    //start a transaction
                    System.out.println("Starting new transaction");
                    session.beginTransaction();

//                    // query student
//
//                    List<Student> theStudents=
//                            session
//                            .createQuery("from Student")
//                            .getResultList();
                    //display students

                    // query student: lastName='Doe'
                    List<Student>  theStudents=session
                            .createQuery("from Student s where s.lastName='Duck'")
                            .getResultList();
                    System.out.println("\n\nStudents with lastName= 'Duck':");
                    displayStudents(theStudents);

                    //commit the transaction
                    System.out.println("Commiting the transaction.");
               session.getTransaction().commit();

                    System.out.println("Done!");
                }

                finally {
                    factory.close();
                    System.out.println("Closing the factory.");
                }



    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempstudent: theStudents){
            System.out.println(tempstudent);
        }
    }
}
