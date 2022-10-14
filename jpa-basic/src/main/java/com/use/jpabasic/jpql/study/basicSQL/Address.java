package com.use.jpabasic.jpql.study.basicSQL;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
