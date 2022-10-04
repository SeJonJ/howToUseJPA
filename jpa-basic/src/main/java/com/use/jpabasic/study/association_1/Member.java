package com.use.jpabasic.study.association_1;

import com.use.jpabasic.study.association_2.Locker;
import com.use.jpabasic.study.association_2.MemberProduct;
import com.use.jpabasic.study.association_2.Product;
import com.use.jpabasic.study.highClassMapping.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "teamMember")
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
// 매핑 정보만을 위한 부모 클래스(superclass)
public class Member extends BaseEntity {

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

    // 연관관계 매핑을 위한 메서드
    // 이것을 통해서 team 을 넣어주면 해당 team 의 members 에 자기자신 객체를 넣는다
    // 이렇게 하면 뒤에서 team.getMembers().add(member) 따로 setter(Team team) 해서 2번 할 필요는 없어진다
   public void addMembers(Team team){
        // 현재 Member 의 Team 에 매개변수로 넘어온 team 을 넣어준다 : member -> team
       this.team = team;

        // this 는 현재 자기 자신 객체 의미한다 : team -> member
        team.getMembers().add(this);
    }

    private String userName;

    // Member 하나당 Locker 하나
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 다대다 매핑에서 memberproduct 테이블을 따로 만들어서 일대다로 변경!!
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
