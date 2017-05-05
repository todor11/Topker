package com.topker.areas.user.service;

import com.topker.areas.user.models.bindingModels.UserPasswordChangeModel;

public interface UserService {

    boolean changePassword(UserPasswordChangeModel userPasswordChangeModel, String username);

    boolean isValidPassword(String userInputOldPassword, String username);

    boolean hasPassword(String username);

    boolean isUsernameTaken(String newUsername);
}
