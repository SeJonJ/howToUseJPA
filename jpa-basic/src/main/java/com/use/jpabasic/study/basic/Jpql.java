package com.use.jpabasic.study.basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Jpql {
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

        /*
            쿼리문도 간단한 단순 쿼리가 있는가하면 복잡한 서브 쿼리를 포함한 쿼리, join 쿼리 등등 여러가지가 있다
            이런 경우에 대해서 JPA 는 어떻게 대처할까? => 정답은 JPQL

            JPA 에서는 JPQL 을 사용해서 복잡한 쿼리문을 사용 할 수 있다.
            여기서 JPQL 과 일반 SQL 의 가장 큰 차이는 JPQL 은 객체(Entity) 를 대상으로 쿼리를 한다는 점!!!
            그래서 일반 쿼리와 약간 결이 다르다
       */
        try{
            // JPQL 사용시 createQuery 를 사용한다 => 매개변수로 jpql, Entity 클래스가 들어간다
            // jpql 에서 from 뒤에 오는게 table 이 아닌 "객체" 가 온다
            // .getResultList 를 사용해서 list 형태로 결과를 받아올 수 잇다
            List<Member> list = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            // 결과 표시
           list.forEach(m -> {
               System.out.println(m.toString());
            });

           /*
              어떻게 활용할까? => 대표적으로 페이징처리할때 사용 가능
           */
            List<Member> newList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // 시작 번호
                    .setMaxResults(10) // 몇개나 출력할지
                    .getResultList();
            newList.forEach(result ->{
               System.out.println(result.toString());
            });


        }catch(Exception e){
            ex.rollback();

        }finally {
            em.close();
        }

        emf.close();
    }
}
