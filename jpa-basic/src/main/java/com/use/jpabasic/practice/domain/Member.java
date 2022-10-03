package com.use.jpabasic.practice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ShopMember")
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue // 전략 생략하면 AUTO
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    // 양방향 연관관계 매핑 => 현재클래스 : member
    // mappedBy 에는 연관관계의 주인쪽에서 현재 클래스를 부를 변수명
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
