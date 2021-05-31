package com.foodstore.model;

import java.util.Date;

public class Order {
    private String id;
    private String itemId;
    private int quantity;
    private float price;
    private Date orderedAt;
    private String deliveryExecutiveId;
    private Date deliveredAt; // ignore this for now while feeding data to DB
    private String paymentId;
    private String addressId;
    private String feedbackId;
    private PaymentDetails paymentDetails;
    private AddressDetails addressDetails;
    private DeliveryExecutiveDetails deliveryExecutiveDetails;
    private Feedback feedback;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getDeliveryExecutiveId() {
        return deliveryExecutiveId;
    }

    public void setDeliveryExecutiveId(String deliveryExecutiveId) {
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }

    public DeliveryExecutiveDetails getDeliveryExecutiveDetails() {
        return deliveryExecutiveDetails;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;

    }

    public void setDeliveryExecutiveDetails(DeliveryExecutiveDetails deliveryExecutiveDetails) {
        this.deliveryExecutiveDetails = deliveryExecutiveDetails;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
}
