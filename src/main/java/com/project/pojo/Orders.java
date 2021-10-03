package com.project.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Orders {
    private String userMail;
    private int orderId;
    private Integer orderStatus;
    private Timestamp orderTime;
    private String orderDiscountCode;
    private String orderAddress;
    private String orderPhone;
    private String orderReceiver;
    private String orderNote;
    private float totals;



    public Orders() {
    }

    public Orders(String userMail, Integer orderStatus, Timestamp orderTime, String orderDiscountCode,
                  String orderAddress, String orderPhone, String orderReceiver, String orderNote) {
        this.userMail = userMail;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.orderDiscountCode = orderDiscountCode;
        this.orderAddress = orderAddress;
        this.orderPhone = orderPhone;
        this.orderReceiver = orderReceiver;
        this.orderNote = orderNote;
    }

    @Basic
    @Column(name = "user_mail")
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_status")
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "order_time")
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "order_discount_code")
    public String getOrderDiscountCode() {
        return orderDiscountCode;
    }

    public void setOrderDiscountCode(String orderDiscountCode) {
        this.orderDiscountCode = orderDiscountCode;
    }

    @Basic
    @Column(name = "order_address")
    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    @Basic
    @Column(name = "order_phone")
    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    @Basic
    @Column(name = "order_receiver")
    public String getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(String orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    @Basic
    @Column(name = "order_note")
    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    @Basic
    @Column(name = "totals")
    public float getTotals() {
        return totals;
    }

    public void setTotals(float totals) {
        this.totals = totals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (orderId != orders.orderId) return false;
        if (userMail != null ? !userMail.equals(orders.userMail) : orders.userMail != null) return false;
        if (orderStatus != null ? !orderStatus.equals(orders.orderStatus) : orders.orderStatus != null) return false;
        if (orderTime != null ? !orderTime.equals(orders.orderTime) : orders.orderTime != null) return false;
        if (orderDiscountCode != null ? !orderDiscountCode.equals(orders.orderDiscountCode) : orders.orderDiscountCode != null)
            return false;
        if (orderAddress != null ? !orderAddress.equals(orders.orderAddress) : orders.orderAddress != null)
            return false;
        if (orderPhone != null ? !orderPhone.equals(orders.orderPhone) : orders.orderPhone != null) return false;
        if (orderReceiver != null ? !orderReceiver.equals(orders.orderReceiver) : orders.orderReceiver != null)
            return false;
        if (orderNote != null ? !orderNote.equals(orders.orderNote) : orders.orderNote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userMail != null ? userMail.hashCode() : 0;
        result = 31 * result + orderId;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderDiscountCode != null ? orderDiscountCode.hashCode() : 0);
        result = 31 * result + (orderAddress != null ? orderAddress.hashCode() : 0);
        result = 31 * result + (orderPhone != null ? orderPhone.hashCode() : 0);
        result = 31 * result + (orderReceiver != null ? orderReceiver.hashCode() : 0);
        result = 31 * result + (orderNote != null ? orderNote.hashCode() : 0);
        return result;
    }
}
