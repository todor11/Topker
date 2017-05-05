package com.topker.areas.productDetails.entities;

import com.topker.areas.categoryProductDetails.entities.CategoryProductDetails;
import com.topker.areas.product.entities.BaseProduct;
import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private double height;

    @Basic
    private double width;

    @Basic
    private double length;

    @Basic
    private double weight;

    @Column(name = "quantity_in_package")
    private int quantityInPackage;

    @Column(name = "single_price", precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private double singlePrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryProductDetails", targetEntity = SimpleProductDetail.class)
    private List<CategoryProductDetails> categoriesProductDetails;

    @OneToOne
    @JoinColumn(name = "product_id")
    private BaseProduct product;

    public ProductDetails() {
        this.setCategoriesProductDetails(new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<CategoryProductDetails> getCategoriesProductDetails() {
        return categoriesProductDetails;
    }

    public void setCategoriesProductDetails(List<CategoryProductDetails> categoriesProductDetails) {
        this.categoriesProductDetails = categoriesProductDetails;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public int getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(int quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }
}
