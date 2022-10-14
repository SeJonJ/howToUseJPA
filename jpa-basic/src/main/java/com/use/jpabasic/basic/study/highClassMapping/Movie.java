package com.use.jpabasic.basic.study.highClassMapping;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// 부모 엔티티에서 DiscriminatorColumn 를 사용한다면 자식 엔티티에서는 DiscriminatorColumn
// 를 사용해서 DiscriminatorColumn 에 들어값 값 value 의 이름을 바꿔 줄 수 있음
// 기본값은 엔티티명이 들어가며, DiscriminatorValue 안에 들어오는 값으로 변경 가능
@DiscriminatorValue("Moovie")
public class Movie extends Item{

    private String director;
    private String actor;

}
