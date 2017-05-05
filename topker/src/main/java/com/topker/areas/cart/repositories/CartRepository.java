package com.topker.areas.cart.repositories;

import com.topker.areas.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findOneBySessionId(String sessionId);

    @Query(value = "SELECT c FROM Cart AS c " +
            "WHERE c.owner.username = :username ")
    Cart findOneByOwner(@Param("username") String username);
}
