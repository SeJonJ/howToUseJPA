package com.use.jpabasic.study.entityMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MappingTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = Member.builder()
                .userName("pk")
                .age(13)
                .roleType(RoleType.USER)
                .description("hello im pk")
                .build();

        em.persist(member);

        tx.commit();
        em.close();
        emf.close();
    }
}
