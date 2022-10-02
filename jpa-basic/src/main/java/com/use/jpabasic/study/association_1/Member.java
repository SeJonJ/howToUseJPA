package com.use.jpabasic.study.association_1;

import lombok.*;

import javax.persistence.*;

@Entity(name = "teamMember")
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    /*
        아래처럼 단순히 PK 에 해당하는 teamId 를 사용하는 것이 아니라
        Team 객체 전체가 일종의 FK 로서 사용됨 => 객체끼리 관계를 형성
        private Long teamId;
    */


    /*
        team == PK 로 생각한다!!
        이때 주의해야 할 것들이 있다.
        첫째로 중요한 것은 '어떤' 관계를 갖는지 알려주어야 한다 => 1:1, 1:N, N:M 이고, 이때 사용하는 어노테이션이 ManyToOne, ManyToMany  등등이 있다
          - 가장 중요하게 기억해야 할 부분은 어떤 관계를 갖는지 지정할때는 "현재 클래스" 를 기준으로 해야한다.
          - Member : Team 는 N:1 의 관계를 갖기 때문에 ManyToOne 어노테이션을 붙여주어야 한다
        둘째로 중요한 것은 해당 PK 로 어떤 컬럼을 조인하는지 알려주어야 한다.
          - 이때 사용하는 어노테이션이 joinColumn 어노테이션이고, 파라미터로 join 하는 컬럼명을 갖는다
    */

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private String userName;

}
