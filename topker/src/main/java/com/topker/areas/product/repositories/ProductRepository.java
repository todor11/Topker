package com.topker.areas.product.repositories;

import com.topker.areas.product.entities.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<BaseProduct, Long> {

    BaseProduct findOneByName(String name);
}
