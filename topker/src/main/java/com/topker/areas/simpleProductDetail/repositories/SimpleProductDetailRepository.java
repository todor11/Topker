package com.topker.areas.simpleProductDetail.repositories;

import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleProductDetailRepository extends JpaRepository<SimpleProductDetail, Long> {

    @Query(value = "SELECT s FROM SimpleProductDetail AS s " +
            "WHERE s.categoryProductDetails.id = :categoryProductDetailsId ")
    List<SimpleProductDetail> findAllByCategoryProductId(@Param("categoryProductDetailsId") Long categoryProductDetailsId);
}
