package com.foodstore.services.orders;

import com.foodstore.dbhelper.DBConnection;
import com.foodstore.dbhelper.DBConnectionImplementation;
import com.foodstore.model.*;
import com.foodstore.services.menuitems.MenuItemService;
import com.foodstore.services.menuitems.MenuItemServiceImplementation;
import com.foodstore.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderServiceImplementation implements OrderService {
    @Override
    public Order placeOrder(Order order) {
        DBConnectionImplementation db = new DBConnectionImplementation();
        Connection con = db.getConnection();
        Random random = new Random();

        try {
            // Execute a query
            Statement stmt = con.createStatement();
            String paymentId = String.valueOf(random.nextInt(200000));
            order.setPaymentId(paymentId);
            String orderId = String.valueOf(random.nextInt(500000));
            order.setId(orderId);
            String addressId = String.valueOf(random.nextInt(800000));
            order.setAddressId(addressId);

            String deliveryExecId = String.valueOf(random.nextInt(4) + 1);
            order.setDeliveryExecutiveId(deliveryExecId);
            order.setDeliveryExecutiveDetails(getDeliveryExecutiveDetails(deliveryExecId));

            MenuItem menuItem = new MenuItemServiceImplementation().getMenu(order.getItemId());
            if (menuItem != null)
                order.setPrice(order.getQuantity() * menuItem.getPrice());

            stmt.executeUpdate("INSERT INTO payment " + "VALUES('" + paymentId + "','" + orderId + "','" + order.getPaymentDetails().getType() + "','" + order.getPaymentDetails().getCardNumber() + "','" + order.getPaymentDetails().getCardHolderName() + "')");
            stmt.executeUpdate("INSERT INTO addressdetails " + "VALUES('" + addressId + "','" + order.getAddressDetails().getCustomerName() + "','" + order.getAddressDetails().getCustomerNumber() + "','" + order.getAddressDetails().getHouseNumber() + "','" + order.getAddressDetails().getStreet() + "','" + order.getAddressDetails().getArea() + "','" + order.getAddressDetails().getCity() + "','" + order.getAddressDetails().getState() + "','" + order.getAddressDetails().getPincode() + "')");
            stmt.executeUpdate("INSERT INTO orderdetails " + "VALUES('" + orderId + "','" + order.getItemId() + "'," + order.getPrice() + "," + order.getQuantity() + "," + null + ",'" + deliveryExecId + "'," + null + ",'" + paymentId + "','" + addressId + "')");
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> listOrders() {
        DBConnection db = new DBConnectionImplementation();
        Connection con = db.getConnection();
        Statement stmt = null;
        List<Order> orders = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from orderdetails");
            while (rs.next()) {
                Order order = new Order();
                order.setId((rs.getString("id")));
                order.setItemId((rs.getString("itemId")));
                order.setQuantity((rs.getInt("quantity")));
                order.setDeliveryExecutiveId((rs.getString("deliveryExecutiveId")));
                order.setPrice((rs.getInt("price")));
                MenuItem menuItem = new MenuItemServiceImplementation().getMenu(order.getItemId());
                order.setItemId(menuItem.getName());

                DeliveryExecutiveDetails deliveryExecutiveDetails = getDeliveryExecutiveDetails(order.getDeliveryExecutiveId());
                order.setDeliveryExecutiveDetails(deliveryExecutiveDetails);
                orders.add(order);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrderDetails(String orderId) {
        return null;
    }

    @Override
    public String giveFeedback(Feedback feedback) {
        DBConnectionImplementation db = new DBConnectionImplementation();
        Connection con = db.getConnection();
        Random random = new Random();
        try {
            Statement stmt = con.createStatement();

            String feedbackId = String.valueOf(random.nextInt(500000));
            // feedback.setFeedbackId(feedbackId);
            stmt.executeUpdate("INSERT INTO feedback " + "VALUES('" + feedbackId + "','" + feedback.getCustomerName() + "','" + feedback.getMessage() + "','" + feedback.getOrderId() + "')");
            return feedbackId;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private DeliveryExecutiveDetails getDeliveryExecutiveDetails(String execId) {

        DeliveryExecutiveDetails deliveryExecutiveDetails = null;
        try {
            DBConnectionImplementation db = new DBConnectionImplementation();
            Connection con = db.getConnection();
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from deliveryexecutive where id='" + execId + "'");
                while (rs.next()) {
                    deliveryExecutiveDetails = new DeliveryExecutiveDetails();
                    deliveryExecutiveDetails.setId((rs.getString("id")));
                    deliveryExecutiveDetails.setName((rs.getString("name")));
                    deliveryExecutiveDetails.setPhoneNumber((rs.getString("phoneNumber")));
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }

        return deliveryExecutiveDetails;
    }
}
