package com.topker.areas.userPersonalDetails.repositories;

import com.topker.areas.userPersonalDetails.entities.UserPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {
}
