package com.use.jpabasic.practice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // 현재 클래스 : MEMBER => 하나의 MEMBER 에 여러 ORDER 이 올 수 있음 -> 1:N
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    // 양방향 연관관계 => 현재클래스 : OrderItem = 1: N
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    // 주문 상태는 Enum 으로
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 양방향 연관관계 편의 메서드
    public void addOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
