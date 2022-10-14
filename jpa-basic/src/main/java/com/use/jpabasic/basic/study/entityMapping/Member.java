package com.use.jpabasic.basic.study.entityMapping;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="NMember")
@Table(name="Member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq"
                , initialValue = 1)
@TableGenerator(name = "member_tableseq_generator", table = "my_seq",
                pkColumnName = "member_SEQ", allocationSize = 1)
public class Member {

    @Id
    // Auto 는 DB 에 맞춰서 자동으로
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // pk
//
//    // SEQUENCE 를 사용하는 경우 SequenceGenerator 를 추가적으로 사용해서
//    // 시퀸스 제너레이터명과 시퀸스명을 지정 가능하다
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
//    private Long seqId;

    // Table 전략은 시퀸스 전용 테이블을 만들어서 사용하는 것 -> DB 에서 사용하는 시퀸스 테이블을 흉내내는 전략
    // 시퀸스 전략과 마찬가지로 제너레이터 파라미터에 TableGenerator name 을 사용한다
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_tableseq_generator")
//    private Long tableId;


    // 컬럼명과 변수명이 다를 경우 name 파라미터로 지정 가능
    @Column(name="name", nullable = false)
    private String userName; // 이름

    private Integer age; // 나이

    @Enumerated(EnumType.STRING) // Enum 타입의 값이 들어가는 경우 사용하는 어노테이션
    private RoleType roleType; // 역할

    // 날짜, 시간, 날짜+시간 의 정보를 매핑할때 사용하는 어노테이션
    // 참고로 Enum 타입
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 생성 날짜

    // @Temporal 을 사용하거나 아래처럼 LocalDate, LocalDateTime 을 사용해도 무방하다
    private LocalDateTime updateDate; // 수정 날짜

    @Lob
    // varchar 를 넘어서는 엄~~청나게 많은 데이터가 들어 갈 수 있는 타입
    // String 에 달려있는 경우 CLOB
    private String description; // 유저 설명

    @Transient // DB 와 매핑하고 싶지 않은 엔티티 변수에 달림 => DB 매핑 X , 컬럼 생성 X
    private  String tmp;
}
