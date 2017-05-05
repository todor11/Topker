package com.topker.areas.singleProductOrder.entities;

import com.topker.areas.order.entities.Order;
import com.topker.areas.product.entities.BaseProduct;

import javax.persistence.*;

@Entity
@Table(name = "single_product_orders")
public class SingleProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private BaseProduct product;

    @Column(name = "ordered_quantity")
    private int orderedQuantity;

    @Column(name = "single_product_present_price")
    private double singleProductPresentPrice;

    @Column(name = "sum_of_products_present_price")
    private double sumOfProductsPresentPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public SingleProductOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public double getSingleProductPresentPrice() {
        return singleProductPresentPrice;
    }

    public void setSingleProductPresentPrice(double singleProductPresentPrice) {
        this.singleProductPresentPrice = singleProductPresentPrice;
    }

    public double getSumOfProductsPresentPrice() {
        return sumOfProductsPresentPrice;
    }

    public void setSumOfProductsPresentPrice(double sumOfProductsPresentPrice) {
        this.sumOfProductsPresentPrice = sumOfProductsPresentPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
