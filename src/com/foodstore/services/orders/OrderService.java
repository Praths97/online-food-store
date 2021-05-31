package com.foodstore.services.orders;

import com.foodstore.model.Feedback;
import com.foodstore.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderService {

    public Order placeOrder(Order order);     // returns orderId

    public List<Order> listOrders();  // returns all existing orders

    public Order getOrderDetails(String orderId);  // returns all existing orders

    public String giveFeedback(Feedback feedback);
}
