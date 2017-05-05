package com.topker.areas.simpleProductDetail.entities;

import com.topker.areas.categoryProductDetails.entities.CategoryProductDetails;

import javax.persistence.*;

@Entity
@Table(name = "simple_product_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SimpleProductDetail<T> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Basic
    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryProductDetails_id", referencedColumnName = "id")
    private CategoryProductDetails categoryProductDetails;

    public SimpleProductDetail() {
    }

    public SimpleProductDetail(String name) {
        this.setName(name);
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

    public abstract T getValue();

    public abstract void setValue(T value);

    public CategoryProductDetails getCategoryProductDetails() {
        return categoryProductDetails;
    }

    public void setCategoryProductDetails(CategoryProductDetails categoryProductDetails) {
        this.categoryProductDetails = categoryProductDetails;
    }
}
