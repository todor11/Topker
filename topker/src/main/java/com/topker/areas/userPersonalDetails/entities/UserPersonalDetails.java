package com.topker.areas.userPersonalDetails.entities;

import com.topker.areas.address.entities.Address;
import com.topker.areas.phoneNumber.entities.PhoneNumber;
import com.topker.areas.user.entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_personal_details")
public class UserPersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userPersonalDetails", targetEntity = Address.class)
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userPersonalDetails", targetEntity = PhoneNumber.class)
    private Set<PhoneNumber> phoneNumbers;

    @Basic
    private String bulstat;

    @Column(name = "personal_discount")
    private double personalDiscount;

    //TODO validation
    private String email;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserPersonalDetails() {
        this.setAddresses(new HashSet<>());
        this.setPhoneNumbers(new HashSet<>());
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    public double getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
