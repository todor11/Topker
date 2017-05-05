package com.topker.areas.role.services;

import com.topker.areas.role.entities.Role;
import com.topker.areas.role.repositories.RoleRepository;
import com.topker.enums.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = Authority.USER.getFullAppText();

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        int numbOfRoles = this.roleRepository.getNumbOfRoles();
        if (numbOfRoles == 0) {
            this.setRolesToDatabase();
        }

        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    //@Override
    //public UserPersonalDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    //    return null;
    //}

    private void setRolesToDatabase() {
        Authority[] authorities = Authority.values();
        for (int i = 0; i < authorities.length; i++) {
            Authority authority = authorities[i];
            Role role = new Role();
            role.setAuthority(authority.getFullAppText());
            this.roleRepository.save(role);
        }
    }
}
