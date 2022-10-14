package com.use.jpabasic.basic.study.periodType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Address 객체를 만든 후
        Address address = new Address("city", "street", "zipcode");

        // member 와 member2 에 각각 저장
//        Member member = new Member();
//        member.setUserName("user1");
//        member.setHomeAddress(address);
//        member.setWorkDate(new WorkDate(LocalDate.of(2020,7,21), LocalDate.now()));
//        em.persist(member);

//        // 기존의 address 에 담긴 값을 그대로 복사한 copyAddress 객체를 새로 생성하고 이를 member2 에 넣는다
//        Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//        Member member2 = new Member();
//        member2.setUserName("user2");
//        member2.setHomeAddress(copyAddress);
//        member2.setWorkDate(new WorkDate(LocalDate.of(2020,7,22), LocalDate.now()));
//        em.persist(member2);
//
//        Address homeAddress = member.getHomeAddress();

//        AddressEntity addressEntity = new AddressEntity(address);
//        em.persist(addressEntity);
//
        AddressEntity second = new AddressEntity(new Address("22", "2", "2"));
        em.persist(second);

        Member member = new Member();
        member.setUserName("user1");
        em.persist(member);

        member.addAddress(second);

//        member.getAddressHistory().add(addressEntity);

        // List 타입 저장
//        member.getAddressHistory().add(addressEntity);
//        member.getAddressHistory().add(new AddressEntity(address.getCity()+"1", address.getStreet(), address.getZipcode()));
//        member.getAddressHistory().add(new AddressEntity(address.getCity()+"12", address.getStreet(), address.getZipcode()));

        // Set 타입 저장
//        member.getFavoriteFoods().add("치킨");
//        member.getFavoriteFoods().add("피자");
//        member.getFavoriteFoods().add("순대");



        em.flush();
        em.clear();

//        System.out.println("=============== 조회 start ============");
//        Member findMember = em.find(Member.class, member.getId());
//        System.out.println("====== 실제 컬렉션 값 타입을 가져올 때 Query 발생 ======");
//        findMember.getFavoriteFoods().forEach(food -> {
//            System.out.println("food : " + food);
//        });
        System.out.println("=============== 조회 start ============");
        Member findMember = em.find(Member.class, member.getId());

            /* 앞서 객체 타입 공유 참조에서 이야기했듯이
            *  단순히 setCity 하게 되면 동일한 객체 타입을 참조하고 있는 member 가 있다면
            *  해당 member 의 city 가 전부 바뀌어 버리는 큰 문제가 발생할 수 있다
            *  따라서 setCity 가 아닌 findMember.set 해서 새로운 인스턴스를 생성해서 넣어야 한다
            * */
        // 아래처럼 사용하면 안된다
//        findMember.getHomeAddress().setCity("절대 이렇게 하면 안된다!!!!");
        // 아래처럼 완전히 새로운 인스턴스로 교체하자
//        findMember.setHomeAddress(new Address("city222", "street2222", "zipcode2222"));

        // 컬렉션 타입 수정 : Set 의 경우
//        findMember.getFavoriteFoods().remove("치킨");
//        findMember.getFavoriteFoods().add("한식");

        // 컬렉션 타입 수정 : List 의 경우
//        findMember.getAddressHistory().remove("city12"); 단순히 이렇게 사용하면 DB 에서 안지워진다

       /*
           컬렉션들은 대부분 대상을 찾을 때 기본적으로 equals 를 사용한다
            따라서 equals 를 꼭 오버라이딩해서 사용해야 하는 이유가 바로 이런 컬렉션 값 타입을 사용하면서
            컬렉션 안에 있는 값을 삭제, 변경 하기 위해서 이다.
        */

        // 따라서 List 에서 값을 찾아서 변경할 때는 내가 동일한 값을 갖는 인스턴스(객체)를 가져다 넣는다
        // 이렇게해야지 delete 문이 나가게 된다
//        System.out.println(" ================= 수정 삭제 ==============");
//        findMember.getAddressHistory().remove(new AddressEntity(address.getCity() + "12", address.getStreet(), address.getZipcode()));
//        findMember.getAddressHistory().add(new AddressEntity("newCity", "newStreet", "newZipCode"));

//        em.remove(em.find(AddressEntity.class, 1L));


        tx.commit();
        em.close();
        emf.close();
    }
}
