package com.use.jpabasic.practice;

import com.use.jpabasic.practice.domain.Book;
import com.use.jpabasic.practice.domain.Member;
import com.use.jpabasic.practice.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Book book = new Book();
        book.setName("JPA강의");
        book.setAuthor("김영한");
        book.setIsbn("123456789");
        book.setPrice(38000);
        book.setStockQuantity(10);

        em.persist(book);
        em.flush();

        tx.commit();
        em.close();
        emf.close();
    }
}
