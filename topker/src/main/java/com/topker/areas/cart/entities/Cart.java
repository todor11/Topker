package com.topker.areas.cart.entities;

import com.topker.areas.product.entities.BaseProduct;
import com.topker.areas.user.entities.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carts_base_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "base_product_id"))
    private List<BaseProduct> products;

    @ElementCollection
    private List<Integer> quantities;

    @OneToOne
    @JoinColumn(name="owner_id")
    private User owner;

    private String sessionId;

    public Cart() {
        this.setProducts(new ArrayList<>());
        this.setQuantities(new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addProduct(BaseProduct product) {
        if (this.products.contains(product)) {
            int productIndex = this.products.indexOf(product);
            Integer productQuantity = this.quantities.get(productIndex);
            int newQuantity = productQuantity + 1;
            this.quantities.remove(productIndex);
            this.quantities.add(productIndex, new Integer(newQuantity));
        } else {
            this.products.add(product);
            this.quantities.add(new Integer(1));
        }
    }

    public void removeProduct(BaseProduct product) {
        if (this.products.contains(product)) {
            int productIndex = this.products.indexOf(product);
            if (this.quantities.get(productIndex) == 1) {
                this.quantities.remove(productIndex);
                this.products.remove(product);
            } else {
                Integer productQuantity = this.quantities.get(productIndex);
                productQuantity--;
            }
        }
    }
}
