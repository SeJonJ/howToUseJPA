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
        member.setUserName("aa");

        Team team = new Team();
        team.setTeamName("haaello");

        member.addMembers(team);

        em.persist(team);
        em.persist(member);

        em.flush();
        em.clear();

        em.createQuery("select m from teamMember m", Member.class)
                .getResultList();


//        Member reference = em.getReference(Member.class, 2L);
//        Member findMember = em.find(Member.class, reference.getId());
//        System.out.println("findMember == reference : " + (findMember == reference));
//        System.out.println("findMember : "+findMember.getClass());
//        System.out.println("reference : "+reference.getClass());

        // 즉시 로딩으로 설정해두었기 때문에 member 를 가져오는 순간 team 테이블을 join 해서
        // member 와 함께 team 에 대한 내용을 가져온다
//        Member findMember = em.find(Member.class, member.getId());
//        System.out.println(findMember.getClass() + " " + findMember.getId());
//
//        System.out.println("------------ Member 엔티티 안 Team 내용 가져오기 ----------------");
//
//        System.out.println(findMember.getTeam().getClass()+" "+findMember.getTeam().getTeamName());

        tx.commit();

        em.close();
        emf.close();

    }
}
