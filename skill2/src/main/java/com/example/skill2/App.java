package com.example.skill2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();

        // INSERT
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Product("Laptop", "Gaming Laptop", 75000, 10));
        session.save(new Product("Mouse", "Wireless Mouse", 1200, 50));
        session.save(new Product("Earphones", "Wired Earphones", 200, 150));
        session.save(new Product("Headset", "Gaming Headset", 2000, 10));

        tx.commit();
        session.close();

        System.out.println("Insertion successfully completed");

        // FETCH BY ID
        Session session2 = sf.openSession();
        Product product = session2.get(Product.class, 1);

        if (product != null) {
            System.out.println("\nProduct Retrieved by ID:");
            System.out.println("ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        }

        // UPDATE
        Transaction tx2 = session2.beginTransaction();
        Product updateProduct = session2.get(Product.class, 3);
        if (updateProduct != null) {
            updateProduct.setPrice(150);
            updateProduct.setQuantity(300);
            System.out.println("\nUpdated product details:");
            System.out.println("ID: " + updateProduct.getProductId());
            System.out.println("Name: " + updateProduct.getName());
            System.out.println("Price: " + updateProduct.getPrice());
            System.out.println("Quantity: " + updateProduct.getQuantity());
        }
        tx2.commit();

        // DELETE
        Transaction tx3 = session2.beginTransaction();
        Product deleteProduct = session2.get(Product.class, 2);
        if (deleteProduct != null) {
            System.out.println("\nProduct discontinued:");
            System.out.println("ID: " + deleteProduct.getProductId());
            System.out.println("Name: " + deleteProduct.getName());
            session2.delete(deleteProduct);
            System.out.println("Product deleted successfully");
        }
        tx3.commit();

        session2.close();
        sf.close();
    }
}