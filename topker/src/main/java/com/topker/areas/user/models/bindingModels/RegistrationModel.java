package com.topker.areas.user.models.bindingModels;

import com.topker.areas.phoneNumber.entities.PhoneNumber;
import com.topker.customValidations.passwordsMatching.IsPasswordsMatching;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@IsPasswordsMatching
public class RegistrationModel {

    @Size(min = 5, message = "errorMessages.username")
    private String username;

    @Size(min = 5, message = "errorMessages.password")
    private String password;

    private String confirmPassword;

    private Set<String> addresses;

    private Set<String> phoneNumbers;

    private String bulstat;

    private String email;

    public RegistrationModel() {
        this.setAddresses(new HashSet<>());
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
