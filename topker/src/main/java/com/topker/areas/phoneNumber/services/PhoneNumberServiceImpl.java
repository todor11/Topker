package com.topker.areas.phoneNumber.services;

import com.topker.areas.phoneNumber.entities.PhoneNumber;
import com.topker.areas.phoneNumber.models.bindingModels.PhoneNumberCreationModel;
import com.topker.areas.phoneNumber.repositories.PhoneNumberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final ModelMapper modelMapper;

    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberServiceImpl(ModelMapper modelMapper, PhoneNumberRepository phoneNumberRepository) {
        this.modelMapper = modelMapper;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public PhoneNumber create(PhoneNumberCreationModel phoneNumberCreationModel) {
        PhoneNumber phoneNumber = this.modelMapper.map(phoneNumberCreationModel, PhoneNumber.class);

        return this.phoneNumberRepository.save(phoneNumber);
    }
}
