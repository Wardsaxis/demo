package com.revature.food4delivery.model;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Column(name ="date_created")
    @CreationTimestamp
    private Date date;

    @Column(name = "order_tracking_numer")
    private String order_tracking_number;

    @Column(name = "total_price")
    private double total_price;

    @Column(name = "total_quantity")
    private int total_quantity;

    @Column(name = "status")
    private boolean status;

    @Column(name = "address_id")
    private Long address_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orders", referencedColumnName = "id_address")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();
    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }
}