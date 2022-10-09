package com.use.jpabasic.study.periodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

// 반드시 기본 생성자가 필요함!!!!
@Embeddable
// 객체 참조에 따른 방법 1 : @Setter : setter 를 없애버린다
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;

    private String street;

    private String zipcode;

    // 객체 참조에 따른 방법 2 : private 으로 setter 생성


    public void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    // 객체의 값 타입 비교는 equals 로 사용해서 비교해야함
    // == 를 사용하면 참조값을 비교하기 때문에 서로 다른 인스턴스에서는 무조건 false 가 나온다
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
