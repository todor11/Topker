package com.topker.areas.user.models.bindingModels;

import com.topker.customValidations.passwordsMatching.IsPasswordsMatching;

import javax.validation.constraints.Size;

@IsPasswordsMatching
public class UserPasswordChangeModel {

    private String oldPassword;

    @Size(min = 5, message = "errorMessages.password")
    private String password;

    private String confirmPassword;

    public UserPasswordChangeModel() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
