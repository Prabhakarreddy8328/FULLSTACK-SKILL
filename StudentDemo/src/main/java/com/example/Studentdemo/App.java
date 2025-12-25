package com.example.Studentdemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        /* Students from CSE */
        System.out.println("Students from CSE:");
        Query<Student> q1 = session.createQuery(
                "FROM Student WHERE department = :dept", Student.class);
        q1.setParameter("dept", "CSE");
        q1.list().forEach(s -> System.out.println(s.getName()));
        System.out.println();

        /* Students with marks > 70 */
        System.out.println("Students with marks > 70:");
        Query<Student> q2 = session.createQuery(
                "FROM Student WHERE marks > ?1", Student.class);
        q2.setParameter(1, 70);
        q2.list().forEach(s -> System.out.println(s.getName()));
        System.out.println("Total Students: " +
                session.createQuery("SELECT count(s.id) FROM Student s", Long.class)
                        .getSingleResult());
        System.out.println();

        /* Sorted by marks */
        System.out.println("Students Sorted by Marks:");
        Query<Student> q4 = session.createQuery(
                "FROM Student ORDER BY marks DESC", Student.class);
        q4.list().forEach(s ->
                System.out.println(s.getName() + " - " + s.getMarks()));
        System.out.println();

        /* Pagination */
        System.out.println("First 3 Students:");
        Query<Student> q5 = session.createQuery(
                "FROM Student", Student.class);
        q5.setFirstResult(0);
        q5.setMaxResults(3);
        q5.list().forEach(s -> System.out.println(s.getName()));
        System.out.println("\n");

        /* Criteria API */
        System.out.println("HCQL (Criteria) - Marks > 80:");
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        cq.select(root).where(cb.gt(root.get("marks"), 80));
        session.createQuery(cq)
                .getResultList()
                .forEach(s -> System.out.println(s.getName()));

        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}