package com.topker.areas.user.serviceImpl;

import com.topker.areas.cart.entities.Cart;
import com.topker.areas.cart.repositories.CartRepository;
import com.topker.areas.role.services.RoleService;
import com.topker.areas.user.entities.FacebookUser;
import com.topker.areas.user.entities.SocialUser;
import com.topker.areas.user.repositories.SocialUserRepository;
import com.topker.areas.user.service.FacebookUserService;
import com.topker.areas.userPersonalDetails.entities.UserPersonalDetails;
import com.topker.areas.userPersonalDetails.models.bindingModels.UserPersonalDetailsRegistrationModel;
import com.topker.areas.userPersonalDetails.services.UserPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacebookUserServiceImpl implements FacebookUserService {

    private final SocialUserRepository socialUserRepository;

    private final RoleService roleService;

    private final UserPersonalDetailsService userPersonalDetailsService;

    private final CartRepository cartRepository;

    @Autowired
    public FacebookUserServiceImpl(SocialUserRepository socialUserRepository, RoleService roleService, UserPersonalDetailsService userPersonalDetailsService, CartRepository cartRepository) {
        this.socialUserRepository = socialUserRepository;
        this.roleService = roleService;
        this.userPersonalDetailsService = userPersonalDetailsService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null) {
            socialUser = registerUser(facebookUser);
        }

        loginUser(socialUser);
    }

    private FacebookUser registerUser(User facebookUser) {
        FacebookUser user = new FacebookUser();
        user.setUsername(facebookUser.getEmail());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        UserPersonalDetailsRegistrationModel userPersonalDetailsRegistrationModel = new UserPersonalDetailsRegistrationModel();
        userPersonalDetailsRegistrationModel.setEmail(facebookUser.getEmail());
        UserPersonalDetails userPersonalDetails = this.userPersonalDetailsService.create(userPersonalDetailsRegistrationModel);
        user.setUserPersonalDetails(userPersonalDetails);
        userPersonalDetails.setUser(user);
        Cart cart = new Cart();
        cart = this.cartRepository.save(cart);
        cart.setOwner(user);
        user.setCart(cart);
        this.socialUserRepository.save(user);
        return user;
    }

    private void loginUser(SocialUser socialUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser, null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
