package com.use.jpabasic.jpql.study.basicSQL;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "jpql_product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;
    private int stockAmount;

}
