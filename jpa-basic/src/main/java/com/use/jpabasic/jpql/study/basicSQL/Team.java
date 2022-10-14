package com.use.jpabasic.jpql.study.basicSQL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "jpql_team")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        this.members.add(member);
        member.setTeam(this);
    }
}
