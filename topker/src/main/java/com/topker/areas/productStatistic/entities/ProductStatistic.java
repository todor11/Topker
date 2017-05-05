package com.topker.areas.productStatistic.entities;

import com.topker.areas.product.entities.BaseProduct;
import com.topker.areas.review.entities.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_statistics")
public class ProductStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="product_id")
    private BaseProduct product;

    @Basic
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productStatistic", targetEntity = Review.class)
    private List<Review> allReviews;

    @Column(name = "quantity_sold")
    private long quantitySold;

    @OneToOne
    private Review averageRating;

    public ProductStatistic() {
        this.setAllReviews(new ArrayList<>());
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public List<Review> getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(List<Review> allReviews) {
        this.allReviews = allReviews;
    }

    public long getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(long quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Review getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Review averageRating) {
        this.averageRating = averageRating;
    }
}
