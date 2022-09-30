package com.use.jpabasic.basic;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
*  DB 테이블과 매핑하기 위한 클래스 ( 보통 DTO ) 에 @Entity 어노테이션을 붙인다
*  이때 PK 에 해당하는 변수에는 @Id 어노테이션을 붙여야 한다
*
* */
@Entity // DB 테이블과 매핑 후 데이터를 가져와 저장하기 위한 클래스에 붙이는 어노테이션
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id // PK 변수
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
