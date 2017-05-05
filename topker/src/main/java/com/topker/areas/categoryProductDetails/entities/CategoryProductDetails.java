package com.topker.areas.categoryProductDetails.entities;

import com.topker.areas.productDetails.entities.ProductDetails;
import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "category_product_details")
public class CategoryProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryProductDetails", targetEntity = SimpleProductDetail.class)
    private List<SimpleProductDetail> simpleProductDetails;

    @ManyToOne
    @JoinColumn(name = "productDetails_id", referencedColumnName = "id")
    private ProductDetails productDetails;

    public CategoryProductDetails() {
        this.setSimpleProductDetails(new ArrayList<>());
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

    public List<SimpleProductDetail> getSimpleProductDetails() {
        return simpleProductDetails;
    }

    public void setSimpleProductDetails(List<SimpleProductDetail> simpleProductDetails) {
        this.simpleProductDetails = simpleProductDetails;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public void addSimpleProductDetail(SimpleProductDetail simpleProductDetail) {
        this.simpleProductDetails.add(simpleProductDetail);
    }
}
