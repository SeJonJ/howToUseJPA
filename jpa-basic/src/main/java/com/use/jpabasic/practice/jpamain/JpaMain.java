package com.use.jpabasic.practice.jpamain;

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

        // Order_id = 1 인 Member 를 가져오기 위해서
        // 일단 Order_id = 1 인 Order 객체를 가져오고 나서
        Order order = em.find(Order.class, 1L);
        // 다시 Member_ID 에 맞는 member 를 꺼내와야 한다
        Member member = em.find(Member.class, order.getMemberId());

        tx.commit();
        em.close();
        emf.close();
    }
}
