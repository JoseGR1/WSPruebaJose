package com.edu.umg.dao;

import com.edu.umg.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDAO {
    private SessionFactory sessionFactory;

    public StudentDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Student> students = session.createQuery("from Student", Student.class).list();

        session.getTransaction().commit();
        session.close();

        return students;
    }

    public Student getStudentById(Long id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    public void addStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    public void updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    public void deleteStudent(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
        }
        transaction.commit();
        session.close();
    }
}
