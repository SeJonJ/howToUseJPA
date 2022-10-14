package com.use.jpabasic.basic.practice.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name ="CATEGORY_ID")
    private Long id;

    private String name;

    // JPA 는 자기 자신을 조인 가능 : 부모 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // 자식 카테고리
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 다대다 매핑
    // joinTable 을 통해서 임의로 테이블 생성 후 join 하게 됨
    // 이때 joinColumns 와 inverseJoinColumns 를 사용하는데
    // 각각 joinColumns 는 현재 자신의 엔티티에서 조인하는 joinColumn 을 넣어주고
    // inverseJoinColumns 에는 반대 - 다른 Many 에 해당하는 엔티티 - 엔티티에서 조인하는 joincolumn 을 넣어주면 된다    @ManyToMany
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();
}
