package com.topker.areas.category.entities;

import com.topker.areas.product.entities.BaseProduct;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "promotions", targetEntity = BaseProduct.class)
    private Set<BaseProduct> products;

    public Category() {
        this.setProducts(new HashSet<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<BaseProduct> products) {
        this.products = products;
    }

    public void addProduct(BaseProduct product) {
        this.products.add(product);
    }
}
