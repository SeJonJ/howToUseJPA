package com.use.jpabasic.study.persistence;

import com.use.jpabasic.study.basic.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            // 엔티티 수정 - 변경 감지
            // 영속 엔티티 조회
            Member memberC = em.find(Member.class, "6L");

            // 영속 엔티티 수정
            memberC.setName("hi");
            memberC.setId(8L);

            // 아래와 같은 update 코드 없어도 됨됨
            // em.updae(memberC);

            // 다만!! commit 은 필수
            et.commit();

            /* 플러시 하는 방법 */
            // 1. 강제로 플러시
            em.flush();

            // 2. commit 시
            et.commit();

            // 3. JPQL 쿼리 실행 시 => 자동 호출
            List<Member> list  = em.createQuery("select m from Member m", Member.class).getResultList();

            /* 준영속 상태로 만드는 방법 */
            // 영속성 상태
            Member memberD = em.find(Member.class, 6L);

            // 1. detach 사용 => 특정한 엔티티 하나만 준영속 상태로 변환3
            em.detach(memberD);

            // 아래처럼 엔티티를 변경해도 update query 가 생성되지 않음
            memberD.setId(99L);
            et.commit();

            // 2. clear => 영속성 컨텍스트를 완전히 초기화, 즉 1차 캐시 전체 초기화
            em.clear();

            // 3. close => 영속성 컨텍스트를 종료
            em.close();

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();

    }
}
