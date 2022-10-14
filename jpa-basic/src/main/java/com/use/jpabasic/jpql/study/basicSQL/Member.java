package com.use.jpabasic.jpql.study.basicSQL;

import lombok.*;

import javax.persistence.*;

@Entity(name = "jpql_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
