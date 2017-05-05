package com.topker.areas.role.services;

import com.topker.areas.role.entities.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RoleService { // extends UserDetailsService

    Role getDefaultRole();
}