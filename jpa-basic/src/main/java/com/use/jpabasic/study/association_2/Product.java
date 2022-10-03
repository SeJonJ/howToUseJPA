package com.use.jpabasic.study.association_2;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 다대다 매핑에서 memberproduct 테이블을 따로 만들어서 일대다로 변경!!
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
