package com.topker.areas.brand.repositories;

import com.topker.areas.brand.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findOneByName(String name);
}
