package com.topker.areas.simpleProductDetail.repositories;

import com.topker.areas.simpleProductDetail.entities.StringValueSimpleProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringValueSimpleProductDetailRepository extends JpaRepository<StringValueSimpleProductDetail, Long> {
}
