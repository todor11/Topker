package com.topker.areas.address.models.viewModels;

public class AddressViewModel {

    private long id;

    private String fullAddress;

    public AddressViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
