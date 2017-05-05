package com.topker.areas.productStatistic.repositories;

import com.topker.areas.productStatistic.entities.ProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatisticRepository extends JpaRepository<ProductStatistic, Long> {

}
