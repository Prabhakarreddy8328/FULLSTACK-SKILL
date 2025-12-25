package com.example.student_CRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAO {
	public void saveStudent(Student s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(s);

        tx.commit();
        session.close();
    }

    // UPDATE
    public void updateStudent(Student s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(s);

        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        if (s != null) {
            session.delete(s);
        }

        tx.commit();
        session.close();
    }

    // READ (LIST)
    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> list =
                session.createQuery("from Student", Student.class).list();

        session.close();
        return list;
    }
}
