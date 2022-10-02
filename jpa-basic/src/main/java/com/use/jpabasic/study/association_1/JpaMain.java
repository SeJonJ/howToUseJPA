package com.use.jpabasic.study.association_1;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 단방향 관계 설정 후에는 아래와 같이 쉽게 가져올 수 있다
        // 34 번 유저가 속한 팀의 이름을 구하기 위해서는
        Member member = em.find(Member.class, 34L);

        // Team 과 관계설정이 되어있음으로 바로 가져올 수 있다
        String teamName = member.getTeam().getTeamName();

        // teamName 을 바꿀때도 기존처럼 set 을 사용하면 된다
        Team team = member.getTeam();

        // 팀이름을 변경
        team.setTeamName("TeammmmB");
        // 변경된 team 객체를 member 에 set
        member.setTeam(team);

        /* ------------------------  */
        System.out.println("---------- 양뱡향 연관관계 --------------");
        List<Member> list = member.getTeam().getMembers();
        list.forEach(m ->{
            System.out.println(m.toString());
        });


        tx.commit();
        em.close();
        emf.close();
    }
}
