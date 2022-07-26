package com.revature.food4delivery.service;


import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.revature.food4delivery.dto.Purchase;
import com.revature.food4delivery.dto.PurchaseResponse;
import com.revature.food4delivery.model.Order;
import com.revature.food4delivery.model.OrderItem;
import com.revature.food4delivery.model.User;
import com.revature.food4delivery.repo.UserRepo;
@Service
public class OrderServiceImpl implements OrderService{
    private UserRepo userRepo;

    public OrderServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrder_tracking_number(orderTrackingNumber);
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        order.setAddress(purchase.getOrderAddress());
        User user = purchase.getCustomer();
        String theEmail = user.getEmail();
        User userDB = userRepo.findByEmail(theEmail);
        if(userDB!=null)
        {
            user=userDB;
        }
        user.add(order);
        userRepo.save(user);
        return new PurchaseResponse(orderTrackingNumber);
    }