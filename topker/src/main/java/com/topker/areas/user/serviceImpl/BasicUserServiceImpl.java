package com.topker.areas.user.serviceImpl;

import com.topker.areas.cart.entities.Cart;
import com.topker.areas.cart.repositories.CartRepository;
import com.topker.areas.role.services.RoleService;
import com.topker.areas.user.entities.BasicUser;
import com.topker.areas.user.entities.User;
import com.topker.areas.user.models.bindingModels.RegistrationModel;
import com.topker.areas.user.repositories.BasicUserRepository;
import com.topker.areas.user.service.BasicUserService;
import com.topker.areas.userPersonalDetails.entities.UserPersonalDetails;
import com.topker.areas.userPersonalDetails.models.bindingModels.UserPersonalDetailsRegistrationModel;
import com.topker.areas.userPersonalDetails.services.UserPersonalDetailsService;
import com.topker.configs.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicUserServiceImpl implements BasicUserService {

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    private final UserPersonalDetailsService userPersonalDetailsService;

    private final BasicUserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CartRepository cartRepository;

    @Autowired
    public BasicUserServiceImpl(ModelMapper modelMapper, RoleService roleService,
                                UserPersonalDetailsService userPersonalDetailsService, BasicUserRepository userRepository, CartRepository cartRepository) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.userPersonalDetailsService = userPersonalDetailsService;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void register(RegistrationModel registrationModel) {
        BasicUser user = this.modelMapper.map(registrationModel, BasicUser.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        UserPersonalDetailsRegistrationModel userPersonalDetailsRegistrationModel = new UserPersonalDetailsRegistrationModel();
        userPersonalDetailsRegistrationModel.setAddresses(registrationModel.getAddresses());
        userPersonalDetailsRegistrationModel.setPhoneNumbers(registrationModel.getPhoneNumbers());
        userPersonalDetailsRegistrationModel.setBulstat(registrationModel.getBulstat());

        UserPersonalDetails userPersonalDetails = this.userPersonalDetailsService.create(userPersonalDetailsRegistrationModel);
        user.setUserPersonalDetails(userPersonalDetails);
        userPersonalDetails.setUser(user);

        Cart cart = new Cart();
        cart = this.cartRepository.save(cart);
        cart.setOwner(user);
        user.setCart(cart);

        this.userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
