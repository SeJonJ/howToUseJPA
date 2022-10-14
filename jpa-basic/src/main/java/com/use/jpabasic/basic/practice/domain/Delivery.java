package com.use.jpabasic.basic.practice.domain;

import javax.persistence.*;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;

    private String street;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DelverStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

}
