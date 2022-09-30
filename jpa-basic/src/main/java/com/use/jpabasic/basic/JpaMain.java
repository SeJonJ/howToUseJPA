package com.use.jpabasic.basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /*
            JPA 를 사용하기 위해서는 EntityManagerFactory 를 선언해야 한다.
            이때 EntityManagerFactory 는 Persistence.createEntityManagerFactory 로 만들게 되고,
            매개변수로 Persistence.xml 에 선언한 persistenceUnitName 를 사용한다
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제로 쿼리를 날리기 위해서는 EntityManager 을 만드는데 이때 emf.createEntityManager 을 사용한다.
        EntityManager em = emf.createEntityManager();

        // JPA 에서는 DB 와 관련되어지는 대다수의 작업에서 트랜잭션이 매우매우 중요하다
        // 따라서 em 을 만든 이후 Transaction 도 같이 만들어서 실행 해주어야 한다.
        EntityTransaction ex = em.getTransaction();

        // 트랜잭션 실행
        ex.begin();

        /*
        * 아래는 정석 코드
        * try 로 감싸서 쿼리를 날리고
        * 만약 에러가 발생하면 그대로 rollback() 하는 것
        * */
        try{
            Member member = new Member();
            member.setId(2L);
            member.setName("second");

            // em.persist 는 Insert 문 !! => member 객체 내용을 DB 에 저장
            em.persist(member);


            // find 는 select 문 => 매개변수로 DTO 클래스와 primarykey 를 받는다
            Member findMem = em.find(Member.class, 2L);
            System.out.println(findMem.toString());

            System.out.println("----- JPA 에서 set 으로 Entity 수정 ------");

            // set 은 update 문 =>
            // JPA 를 통해서 Entity 를 가져오면 해당 Entity 를 JPA 가 관리하게 됨
            // 여기서 중요한 점이 JPA 가 해당 Entity 의 변경 유무를 트랜잭션 커밋 시점에 체크하게 된다.
            // 만약 변경이 있다면 JPA 가 알아서 update 문을 만들어서 DB 의 내용을 변경하게 된다
            findMem.setName("jpa 최고");
            System.out.println(findMem.toString());

            // Detach
            em.detach(findMem);

            // remove 는 delete 문
            em.remove(findMem);

            // 트랜잭션 커밋
            ex.commit();

        }catch(Exception e){
            ex.rollback();

        }finally {
            em.close();
        }

        emf.close();

    }
}
