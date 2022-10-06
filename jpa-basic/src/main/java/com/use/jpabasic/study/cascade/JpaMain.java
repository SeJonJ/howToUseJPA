package com.use.jpabasic.study.cascade;

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

        // CASCADE 가 있는 경우 em.persist 를 한번만 호출해도 된다!!
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        parent.addChild(child1);
        parent.addChild(child2);

        // CASCADE 를 설정한 경우 한번만 실행해도 parent 에서 CASCADE 설정이 되어 있음으로
        // 한번만 해도 무방함
        em.persist(parent);
//        em.persist(child1);
//        em.persist(child2);

        em.flush();
        em.clear();

        // 부모 객체의 childList 에서 0 번째 요소를 삭제한다
        // 고아 객체를 삭제하는 delete 가 발생
        Parent findParent = em.find(Parent.class, parent.getId());
        findParent.getChildList().remove(0);

        tx.commit();
        em.close();
        emf.close();
    }
}
