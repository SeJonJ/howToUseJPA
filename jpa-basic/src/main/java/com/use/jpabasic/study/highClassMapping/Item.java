package com.use.jpabasic.study.highClassMapping;

import lombok.*;

import javax.persistence.*;

@Entity(name="newItem")
// 클래스 마다 테이블 생성 전략
// 부모 엔티티는 추상 클래스로 만들어서 사용하는게 적합
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
