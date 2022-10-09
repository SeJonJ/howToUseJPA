package com.use.jpabasic.study.periodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private Member member;

    public AddressEntity(Address address) {
        this.address = address;
    }

    private Address address;

}
