package com.revature.food4delivery.service;


import com.revature.food4delivery.dto.Purchase;
import com.revature.food4delivery.dto.PurchaseResponse;

public interface OrderService {
    PurchaseResponse placeOrder(Purchase purchase);
}
