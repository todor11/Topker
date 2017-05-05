package com.topker.areas.categoryProductDetails.repositories;

import com.topker.areas.categoryProductDetails.entities.CategoryProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductDetailsRepository extends JpaRepository<CategoryProductDetails, Long> {

}
