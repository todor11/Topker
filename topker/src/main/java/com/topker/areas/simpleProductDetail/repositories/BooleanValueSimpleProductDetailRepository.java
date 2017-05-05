package com.topker.areas.simpleProductDetail.repositories;

import com.topker.areas.simpleProductDetail.entities.BooleanValueSimpleProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooleanValueSimpleProductDetailRepository extends JpaRepository<BooleanValueSimpleProductDetail, Long> {
}
