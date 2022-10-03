package com.use.jpabasic.study.association_2;

import com.use.jpabasic.study.association_1.Member;

import javax.persistence.*;

@Entity
public class MemberProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
