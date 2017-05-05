package com.topker.areas.userPersonalDetails.services;

import com.topker.areas.userPersonalDetails.entities.UserPersonalDetails;
import com.topker.areas.userPersonalDetails.models.bindingModels.UserPersonalDetailsRegistrationModel;

public interface UserPersonalDetailsService {

    UserPersonalDetails create(UserPersonalDetailsRegistrationModel userPersonalDetailsRegistrationModel);
}
