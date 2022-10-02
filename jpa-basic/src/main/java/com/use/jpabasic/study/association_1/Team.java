package com.use.jpabasic.study.association_1;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String teamName;

    /*
        Member : Team 일 때는 Member 클래스를 기준으로 N:1 의 관계가 성립했다
        규칙 1 ) 그렇다면 자연스럽게 Team : Member 의 관계는 현재 클래스인 Team 을 기준으로
            - 1 : N 의 관계가 성립하며 이에 따라서 OneToMany 어노테이션을 사용한다.
        규칙 2 ) 동시에 mappedBy 파라미터를 사용하는데 이 파라미터의 값에는
            - 연관관계 주인쪽에서 현재 클래스를 지정하는 변수를 넣어준다 => Member 에서 현재 클래스 Team 을 지정하는 변수 team
            - 따라서 Team 쪽에서는 Member 와 매핑되는 이름이 team 임으로 파라미터의 값을 team 으로 적는것이다

        !!!! 사실 mappedBy 는 아주 어렵고, 중요한 개념으로 뒤에 다시 설명하도록 하겠다 !!!!
    */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
