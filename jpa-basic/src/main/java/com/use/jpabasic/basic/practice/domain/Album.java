package com.use.jpabasic.basic.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "domain_album")
@Setter @Getter
public class Album extends Item{

    private String artist;
    private String etc;
}
