package com.topker.areas.userPersonalDetails.models.bindingModels;


import java.util.HashSet;
import java.util.Set;

public class UserPersonalDetailsRegistrationModel {

    private Set<String> addresses;

    private Set<String> phoneNumbers;

    private String bulstat;

    private String email;

    public UserPersonalDetailsRegistrationModel() {
        this.setAddresses(new HashSet<>());
        this.setPhoneNumbers(new HashSet<>());
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
