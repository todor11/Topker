package com.topker.areas.singleProductOrder.repositories;

import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleProductOrderRepository extends JpaRepository<SingleProductOrder, Long> {
}
