package com.edu.umg.dao;

import com.edu.umg.entities.Student;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.SessionFactory;

public class StudentDAO {

    // Obtener la fábrica de sesiones desde HibernateUtil
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        Session session = null;
        List<Student> students = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            students = session.createQuery("from Student", Student.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return students;
    }

    // Obtener un estudiante por ID
    public Student getStudentById(Long id) {
        Session session = null;
        Student student = null;
        try {
            session = sessionFactory.openSession();
            student = session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return student;
    }

    // Agregar un nuevo estudiante
    public void addStudent(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // Actualizar un estudiante existente
    public void updateStudent(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // Eliminar un estudiante por ID
    public void deleteStudent(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
