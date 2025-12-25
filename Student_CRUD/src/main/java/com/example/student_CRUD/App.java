package com.example.student_CRUD;

import java.util.List;

public class App {
    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // INSERT
        Student s1 = new Student();
        s1.setName("RAJA");
        s1.setDepartment("CSE");
        s1.setMarks(90);
        dao.saveStudent(s1);

        Student s2 = new Student();
        s2.setName("Ramesh");
        s2.setDepartment("ECE");
        s2.setMarks(70);
        dao.saveStudent(s2);

        Student s3 = new Student();
        s3.setName("Kannan");
        s3.setDepartment("ECE");
        s3.setMarks(80);
        dao.saveStudent(s3);

        // READ (LIST)
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
        	System.out.println(s.getId() + " " + s.getName()+" "+s.getDepartment()+" "+s.getMarks());
        }

        System.out.println("Operation completed");
    }
}
