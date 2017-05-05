package com.topker.areas.brand.entities;

import com.topker.areas.product.entities.BaseProduct;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "brand", targetEntity = BaseProduct.class)
    private Set<BaseProduct> products;

    public Brand() {
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
        if (product != null) {
            this.products.add(product);
        }
    }

    public void removeProduct(BaseProduct product) {
        this.products.remove(product);
    }
}
