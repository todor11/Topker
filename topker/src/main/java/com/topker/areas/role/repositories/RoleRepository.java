package com.topker.areas.role.repositories;

import com.topker.areas.role.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByAuthority(String authority);

    @Query(value = "SELECT COUNT(r.id) FROM Role AS r")
    int getNumbOfRoles();
}
