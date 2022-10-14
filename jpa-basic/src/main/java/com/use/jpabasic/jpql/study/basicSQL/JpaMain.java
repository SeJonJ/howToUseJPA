package com.use.jpabasic.jpql.study.basicSQL;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        for(int i=0; i<50; i++){
            Member member = new Member();
            member.setUsername("member"+i);
            member.setAge(i);
            em.persist(member);
        }

//        TypedQuery<Member> query = em.createQuery("select m from jpql_member m", Member.class);
//
//        // getResultList() : 쿼리가 하나 이상일 때, 리스트로 반환 => 결과가 없으면 빈 리스트 반환
//        List<Member> members = query.getResultList();
//
//        // getSingleResult() : 쿼리가 무조건 하나일 때, 정확히 하나일 때 => 결과가 2개 이상이거나 없으면 모두 에러
//        // 참고로 이 문제는 SpringData JPA 를 사용할 시 해결해주는데
//        // 사실 여기서도 결국 try~catch 해서 null 이나 optional 로 return 한다
//        Member member2 = query.getSingleResult();
//
//        members.forEach(mem ->{
//            System.out.println(mem.getId() + " " + mem.getUsername());
//        });
//
//        System.out.println(member2.getId() + " " + member2.getUsername());
/*
        이렇게 실행은 불가능!! 이는 Address 는 임베디드 타입임으로, 즉 값 타입임으로 어디에 소속된
                임베디드 타입인지 적어주어야 함
            em.createQuery("Select address from jpql_order o", Address.class)
                .getSingleResult();
*/
//        // 따라서 아래처럼 사용하자 사용 가능
//        em.createQuery("Select o.address from jpql_order o", Address.class)
//                .getSingleResult();

//        // 우리가 아는 일반 qeury 와 가장 비슷
//        em.createQuery("select m.username, m.age from jpql_member m")
//                .getResultList();

//         아래 query 처럼 조회하면 내부적으로 각 파라미터(컬럼) 에 대한 Object[] 이 생성된다.
//         즉, 결과의 첫번째 행에 대한 Object[], 두번째 행에 대한 Object[] ------ 이런식으로
//         Object[] 안에서 0 번째 인덱스의 값은 username, 1 번째 인덱스의 값은 age
//        List resultList = em.createQuery("select m.username, m.age from jpql_member m")
//                .getResultList();
//
//        // 따라서 이를 출력하기 위해서는 다시 캐스팅이 필요하다
//        Object[] username = (Object[]) resultList.get(0);
//
//        Arrays.stream(username).forEach(name ->{
//            System.out.println("object : " + name);
//        });

//        // 그냥 바로 Object[] 타입의 리스트 형식으로 받아서 출력하기
//        List<Object[]> resultList = em.createQuery("select m.username, m.age from jpql_member m")
//                .getResultList();
//        Object[] result = resultList.get(0);
//
//        System.out.println("name : " + result[0]);
//        System.out.println("age : " + result[1]);

//        // 바로 하나의 객체로 받아올 수 있는 방법도 존재한다 => new 명령어를 사용한다.
//        // 즉 새로 객체를 생성하듯이 query 중간에 new 패키지명.클래스명(쿼리 컬럼1, 쿼리 컬럼2) from 엔티티명", new할때 사용하는 클래스명 으로 사용한다
//        TypedQuery<MemberDto> results = em.createQuery("select new com.use.jpabasic.jpql.study.basicSQL.MemberDto(m.username, m.age) from jpql_member m"
//                ,MemberDto.class);

        // 페이징 처리에 정말 엄청나게 쉽게 할 수 있도록 만든다
        // id 를 기준으로 내림차순 정렬 : order by m.id desc
        // => .getFirstResult(), setMaxResults() 면 끝난다
        List<Member> result = em.createQuery("select m from jpql_member m order by m.id desc", Member.class)
                .setFirstResult(1) // 시작 번호
                .setMaxResults(10) // 가져올 갯수
                .getResultList();// 결과를 list 로 반환

        result.forEach(mem ->{
            System.out.println(mem.toString());
        });


        tx.commit();
        em.close();
        emf.close();

    }
}
