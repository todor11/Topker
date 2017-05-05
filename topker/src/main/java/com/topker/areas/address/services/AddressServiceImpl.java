package com.topker.areas.address.services;

import com.topker.areas.address.entities.Address;
import com.topker.areas.address.models.bindingModels.AddressCreationModel;
import com.topker.areas.address.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(ModelMapper modelMapper, AddressRepository addressRepository) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(AddressCreationModel addressCreationModel) {
        Address address = this.modelMapper.map(addressCreationModel, Address.class);
        return this.addressRepository.save(address);
    }
}
