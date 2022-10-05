package com.use.jpabasic.study.proxy;

import com.use.jpabasic.study.association_1.Member;
import com.use.jpabasic.study.association_1.Team;

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

        Member member = new Member();
        member.setUserName("hello");

        Team team = new Team();
        team.setTeamName("hello");

        member.addMembers(team);

        em.persist(team);
        em.persist(member);

        em.flush();
        em.clear();

        Member reference = em.getReference(Member.class, 2L);
        Member findMember = em.find(Member.class, reference.getId());
        System.out.println("findMember == reference : " + (findMember == reference));
        System.out.println("findMember : "+findMember.getClass());
        System.out.println("reference : "+reference.getClass());

        tx.commit();



        em.close();
        emf.close();




    }
}
