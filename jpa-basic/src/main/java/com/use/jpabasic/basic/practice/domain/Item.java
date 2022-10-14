package com.use.jpabasic.basic.practice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type")
public abstract class Item extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    // 다대다 매핑
    // joinTable 을 통해서 임의로 테이블 생성 후 join 하게 됨
    // 단 이때 연관관계의 주인은 Category 에서 가져갔음으로 여기는 mappedBy 를 사용한다
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
