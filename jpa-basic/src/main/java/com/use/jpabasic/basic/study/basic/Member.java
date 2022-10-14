package com.use.jpabasic.basic.study.basic;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
*  DB 테이블과 매핑하기 위한 클래스 ( 보통 DTO ) 에 @Entity 어노테이션을 붙인다
*  이때 PK 에 해당하는 변수에는 @Id 어노테이션을 붙여야 한다
*
* */

// DB 테이블과 매핑 후 데이터를 가져와 저장하기 위한 클래스에 붙이는 어노테이션
// 이때 Entity 에 name 파라미터를 사용할 수 있는데 이 파라미터로 엔티티 이름을 지정할 수 있다 => 기본값은 클래스명
@Entity(name="Member")
@Table(name="Member") // @Table 은 엔티티와 매핑할 테이블을 지정한다
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id // PK에 해당하는 변수
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
