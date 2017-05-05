package com.topker.areas.user.service;

import com.topker.areas.user.models.bindingModels.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);
}