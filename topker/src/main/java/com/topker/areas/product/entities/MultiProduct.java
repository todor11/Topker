package com.topker.areas.product.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@DiscriminatorValue("multi_product")
public class MultiProduct extends BaseProduct {

    @OneToMany(targetEntity = BaseProduct.class)
    private List<BaseProduct> subProducts;

    public MultiProduct() {
        this.subProducts = new ArrayList<>();
        this.setIsSubProduct(false);
    }

    public List<BaseProduct> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<BaseProduct> subProducts) {
        this.subProducts = subProducts;
    }

    public void addSubProduct(BaseProduct subProduct) {
        if (subProduct == null) {
            return;
        }

        if (!subProduct.getIsSubProduct()) {
            subProduct.setIsSubProduct(true);
        }

        this.subProducts.add(subProduct);
    }

    public void removeSubProduct(BaseProduct subProduct) {
        this.subProducts.remove(subProduct);
    }
}
