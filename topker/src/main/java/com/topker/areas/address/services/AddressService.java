package com.topker.areas.address.services;

import com.topker.areas.address.entities.Address;
import com.topker.areas.address.models.bindingModels.AddressCreationModel;

public interface AddressService {

     Address create(AddressCreationModel addressCreationModel);
}
