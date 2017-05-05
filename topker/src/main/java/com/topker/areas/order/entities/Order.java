package com.topker.areas.order.entities;

import com.topker.areas.address.entities.Address;
import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import com.topker.areas.user.entities.User;
import com.topker.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", targetEntity = SingleProductOrder.class)
    private List<SingleProductOrder> singleProductOrders;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id")
    private Address deliveryAddress;

    @Column(name = "total_sum", precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private double totalSum;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
        this.setSingleProductOrders(new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<SingleProductOrder> getSingleProductOrders() {
        return singleProductOrders;
    }

    public void setSingleProductOrders(List<SingleProductOrder> singleProductOrders) {
        this.singleProductOrders = singleProductOrders;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
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
