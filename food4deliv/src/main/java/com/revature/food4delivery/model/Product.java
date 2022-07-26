package com.revature.food4delivery.model;


import java.util.Locale.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name = "unit_price")
    private double unit_price;

    @Column(name = "available")
    private boolean available;

    @Column(name = "image_url")
    private String imgUrl;


    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
