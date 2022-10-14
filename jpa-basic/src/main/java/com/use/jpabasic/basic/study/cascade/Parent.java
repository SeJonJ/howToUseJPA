package com.use.jpabasic.basic.study.cascade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    String name;

    // orphanRemoval = true : 고아 객체 삭제 설정
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL
            ,orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        child.setParent(this);
        this.getChildList().add(child);
    }
}
