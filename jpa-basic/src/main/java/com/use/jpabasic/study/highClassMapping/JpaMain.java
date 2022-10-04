package com.use.jpabasic.study.highClassMapping;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Movie movie = new Movie();
        movie.setName("해리포터와 죽음의 성물 2");
        movie.setPrice(8500);
        movie.setActor("여러 사람");
        movie.setDirector("누군가");
        em.persist(movie);

        // flush && clear 를 통해서 영속성 컨텍스트를 비워냄
        em.flush();
        em.clear();

        Movie findMovie = em.find(Movie.class, 1L);
        System.out.println(findMovie.getName() + " " + findMovie.getPrice()+" "+findMovie.getActor());

        tx.commit();

        em.close();
        emf.close();
    }
}
