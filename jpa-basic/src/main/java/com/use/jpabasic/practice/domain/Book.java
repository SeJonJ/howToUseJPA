package com.use.jpabasic.practice.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity(name = "domain_book")
@Setter @Getter
public class Book extends Item{
    private String author;
    private String isbn;
}
