package com.use.jpabasic.study.association_1;


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

//        // 단방향 관계 설정 후에는 아래와 같이 쉽게 가져올 수 있다
//        // 34 번 유저가 속한 팀의 이름을 구하기 위해서는
//        Member member = em.find(Member.class, 34L);
//
//        // Team 과 관계설정이 되어있음으로 바로 가져올 수 있다
//        String teamName = member.getTeam().getTeamName();
//
//        // teamName 을 바꿀때도 기존처럼 set 을 사용하면 된다
//        Team team = member.getTeam();
//
//        // 팀이름을 변경
//        team.setTeamName("TeammmmB");
//        // 변경된 team 객체를 member 에 set
//        member.setTeam(team);
//
//        /* ------------------------  */
//        System.out.println("---------- 양뱡향 연관관계 --------------");
//        List<Member> list = member.getTeam().getMembers();
//        list.forEach(m ->{
//            System.out.println(m.toString());
//        });

//        /* 옳지 못한 양방향 연관관계 매핑 */
//        /* 연관관계 주인이 아닌 쪽을 통해서 FK 를 등록하려고 할 때 */
//        // Member 생성
//        Member member = Member.builder()
//                .userName("memberBB")
//                .build();
//        em.persist(member);
//
//        // Team 생성
//        Team team = new Team();
//        team.setTeamName("TeamBB");
//
//        // Team 에 있는 members 에 member 객체 넣기
//        team.getMembers().add(member);
//
//        em.persist(team);

//        /* 옮게 된 양방향 연관관계 매핑 */
//        /* 연관관계 주인쪽에서 FK 를 등록 */
//        // Team 생성
//        Team team = new Team();
//        team.setTeamName("TeamEE");
//        em.persist(team);

//        /* team 과 member 모두 2중으로 세팅한다 */
//        // Member 생성
//        Member member = Member.builder()
//                .userName("memberEE")
//                .build();
//        em.persist(member);
//
//        // 메서드가 동작하면서 Member 안에 team 에 매개변수로 던지는 team 을 세팅하고
//        // 다시 team 의 members 에는 현재 member 자기 자기 객체를 추가한다
//        member.addMembers(team);
//
//        // 만약 이 상태에서 바로 teamDD 의 members 를 가져온다면 어떻게 될까?
//        Team teamDD = em.find(Team.class, team.getId());
//        System.out.println("---- 출력 -----");
//        teamDD.getMembers().forEach(m -> {
//            System.out.println(m.toString());
//        });
//        System.out.println("=========================");

        tx.commit();
        em.close();
        emf.close();
    }
}
