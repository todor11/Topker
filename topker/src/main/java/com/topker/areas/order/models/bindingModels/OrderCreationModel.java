package com.topker.areas.order.models.bindingModels;

import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import com.topker.areas.singleProductOrder.models.viewModels.SingleProductOrderViewModel;
import com.topker.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderCreationModel {

    private String username;

    private LocalDateTime orderDate;

    private List<SingleProductOrderViewModel> singleProductOrders;

    private String deliveryAddress;

    private double totalSum;

    private OrderStatus orderStatus;

    public OrderCreationModel() {
        this.setSingleProductOrders(new ArrayList<>());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<SingleProductOrderViewModel> getSingleProductOrders() {
        return singleProductOrders;
    }

    public void setSingleProductOrders(List<SingleProductOrderViewModel> singleProductOrders) {
        this.singleProductOrders = singleProductOrders;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
