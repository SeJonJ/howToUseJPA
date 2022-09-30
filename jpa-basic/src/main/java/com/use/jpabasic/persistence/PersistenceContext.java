package com.use.jpabasic.persistence;

import com.use.jpabasic.basic.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceContext {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 시작
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            // 비영속
//            Member member = Member.builder()
//                    .id(3L)
//                    .name("persist")
//                    .build();
//
//            // 영속 컨텍스트 1차 캐시에 저장
//            System.out.println("===== Before ====");
//            em.persist(member);
//            System.out.println("===== After =====");

//            // 여기서 조회해올때는 1차 캐시에 있는 내용을 먼저 조회함
//            // 따라서 select 문이 실행되지 X => 즉 쿼리문은 1번만 실행됨
//            Member findMem1 = em.find(Member.class, 3L);
//            Member findMem2 = em.find(Member.class, 3L);
//
//            System.out.println(findMem1.equals(findMem2)); // true
//            System.out.println(findMem1 == findMem2); // true
//            System.out.println("==============================");
//            et.commit();

            // 트랜잭션 쓰기 지연
            Member memberA = Member.builder()
                    .id(6L)
                    .name("persistA")
                    .build();

            Member memberB = Member.builder()
                    .id(7L)
                    .name("persistB")
                    .build();
            System.out.println("===== persist 전 ====");

            em.persist(memberA);
            em.persist(memberB);

            System.out.println("===== persist 후 ====");
            System.out.println("===== commit 전 =====");

            et.commit();
            System.out.println("===== commit 후 =====");

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();

    }
}
