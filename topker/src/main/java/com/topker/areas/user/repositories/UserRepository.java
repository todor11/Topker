package com.topker.areas.user.repositories;

import com.topker.areas.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    T findOneByUsername(String username);
}
