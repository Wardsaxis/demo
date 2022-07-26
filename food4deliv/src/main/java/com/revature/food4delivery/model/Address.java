package com.revature.food4delivery.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zip_code;
    @Column(name = "city")
    private String city;
    private String state;
    
    }
