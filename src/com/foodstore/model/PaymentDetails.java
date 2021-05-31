package com.foodstore.model;

public class PaymentDetails {
    private String id;
    private String orderId;
    private PaymentType type;
    // store only last 4 digits
    private String cardNumber;
    private String cardHolderName;

    enum PaymentType {
        CASH,
        CARD,
        UPI,
        INTERNET_BANKING
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(String type) {
        try {
            PaymentType payment = Enum.valueOf(PaymentType.class, type);
            this.type = payment;
        } catch (Exception e) {

        }

    }

    public String getCardNumber() {

        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}

