package com.use.jpabasic.basic.study.highClassMapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
- 테이블은 서로 다른 테이블을 사용하지만, 공통 매핑 정보가 필요할 때 사용함
- 아래 그림에서 보자면 Member 과 Seller 은 서로 다른 테이블이지만 id 와 name 이라는 공통 속성을 사용하고,
    이렇게 공통된 속성만을 매핑해서 사용하고 싶을 때 쓰는 어노테이션
- 사용하려면 공통 속성을 갖는 superclass 가 필요 => 추상 클래스로 만들어 사용하는게 권장됨
*/
@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
