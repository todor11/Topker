package com.topker.areas.phoneNumber.services;

import com.topker.areas.phoneNumber.entities.PhoneNumber;
import com.topker.areas.phoneNumber.models.bindingModels.PhoneNumberCreationModel;
import com.topker.areas.phoneNumber.repositories.PhoneNumberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface PhoneNumberService {

     PhoneNumber create(PhoneNumberCreationModel phoneNumberCreationModel);
}
