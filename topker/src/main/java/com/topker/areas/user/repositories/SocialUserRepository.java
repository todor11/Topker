package com.topker.areas.user.repositories;

import com.topker.areas.user.entities.SocialUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialUserRepository extends  UserRepository<SocialUser> {
}
