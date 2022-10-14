package com.use.jpabasic.basic.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "domain_movie")
@Getter @Setter
public class Movie extends Item{
    private String director;
    private String actor;
}
