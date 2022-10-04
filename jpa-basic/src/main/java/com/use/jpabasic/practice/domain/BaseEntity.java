package com.use.jpabasic.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
* 공통된 속성을 매핑하기 위한 mappedSuperclass
* 이 클래스는 모든 클래스에 넣어준다
* */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
