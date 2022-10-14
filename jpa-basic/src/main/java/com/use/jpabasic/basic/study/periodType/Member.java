package com.use.jpabasic.basic.study.periodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "period_member")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;


    @Embedded // 값 타입을 사용하는 곳에서 쓰는 어노테이션
    private WorkDate workDate;

    // 한 엔티티 안에서 두 개의 변수(속성)에 동일한 인베디드 타입을 매핑하는 경우
    // => @AttributeOverride 사용 : name 에는 인베디드 타입에서의 변수명, column 에서는 컬럼명을 적어준다
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name="work_city")),
            @AttributeOverride(name="street",
                    column=@Column(name="work_street")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name="work_zipcode"))
    })
    private Address homeAddress;

//    @Embedded
//    private Address workAddress;

    /* 엔티티의 컬렉션 값 타입에 붙이는 어노테이션은 총 2가지 */
    @ElementCollection // 엔티티의 해당 값 타입이 Collection 타입임을 의미하는 어노테이션
    @CollectionTable(name = "favorite_food", // Collection 타입과 매핑되는 테이블을 적어두는 어노테이션
            // joinColumns 는 어떤 컬럼들과 매핑하는지 작성한다
            // @JoinColumn 는 name 에 적힌 테이블에서 어떤 컬럼과 조인할 것인지 작성
            // 즉 FK 에 해당하는 컬럼을 적어주면 된다
            joinColumns = @JoinColumn(name = "member_id"))
    // Address 와는 다르게 하나의 타입으로 되어있는 경우 - 여기서는 Stirng -
    // 아주 예외적으로 Column 이름을 작성할 수 있다
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "address",
//            joinColumns = @JoinColumn(name = "member_id"))
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public void addAddress(AddressEntity addressEntity) {
        this.addressHistory.add(addressEntity);
        addressEntity.setMember(this);
    }

}
