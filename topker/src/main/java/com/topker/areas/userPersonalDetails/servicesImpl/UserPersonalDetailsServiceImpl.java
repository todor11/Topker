package com.topker.areas.userPersonalDetails.servicesImpl;

import com.topker.areas.address.entities.Address;
import com.topker.areas.address.models.bindingModels.AddressCreationModel;
import com.topker.areas.address.services.AddressService;
import com.topker.areas.phoneNumber.entities.PhoneNumber;
import com.topker.areas.phoneNumber.models.bindingModels.PhoneNumberCreationModel;
import com.topker.areas.phoneNumber.services.PhoneNumberService;
import com.topker.areas.userPersonalDetails.entities.UserPersonalDetails;
import com.topker.areas.userPersonalDetails.models.bindingModels.UserPersonalDetailsRegistrationModel;
import com.topker.areas.userPersonalDetails.repositories.UserPersonalDetailsRepository;
import com.topker.areas.userPersonalDetails.services.UserPersonalDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserPersonalDetailsServiceImpl implements UserPersonalDetailsService {

    private final ModelMapper modelMapper;

    private final UserPersonalDetailsRepository userPersonalDetailsRepository;

    private final AddressService addressService;

    private final PhoneNumberService phoneNumberService;

    @Autowired
    public UserPersonalDetailsServiceImpl(ModelMapper modelMapper, UserPersonalDetailsRepository userPersonalDetailsRepository, AddressService addressService, PhoneNumberService phoneNumberService) {
        this.modelMapper = modelMapper;
        this.userPersonalDetailsRepository = userPersonalDetailsRepository;
        this.addressService = addressService;
        this.phoneNumberService = phoneNumberService;
    }

    @Override
    public UserPersonalDetails create(UserPersonalDetailsRegistrationModel userPersonalDetailsRegistrationModel) {
        UserPersonalDetails userPersonalDetails = this.modelMapper.map(userPersonalDetailsRegistrationModel, UserPersonalDetails.class);
        if (userPersonalDetails.getAddresses() != null && !userPersonalDetails.getAddresses().isEmpty()) {
            userPersonalDetails.setAddresses(new HashSet<>());
        }

        if (userPersonalDetails.getPhoneNumbers() != null && !userPersonalDetails.getPhoneNumbers().isEmpty()) {
            userPersonalDetails.setPhoneNumbers(new HashSet<>());
        }

        Set<String> addressesAsString = userPersonalDetailsRegistrationModel.getAddresses();
        if (addressesAsString != null && !addressesAsString.isEmpty()) {
            for (String fullAddress : addressesAsString) {
                AddressCreationModel addressCreationModel = new AddressCreationModel();
                addressCreationModel.setFullAddress(fullAddress);
                Address address = this.addressService.create(addressCreationModel);
                address.setUserPersonalDetails(userPersonalDetails);
                userPersonalDetails.addAddress(address);
            }
        }

        Set<String> phoneNumbersAsString = userPersonalDetailsRegistrationModel.getPhoneNumbers();
        if (phoneNumbersAsString != null && !phoneNumbersAsString.isEmpty()) {
            for (String phoneNumberAsString : phoneNumbersAsString) {
                PhoneNumberCreationModel phoneNumberCreationModel = new PhoneNumberCreationModel();
                phoneNumberCreationModel.setPhoneNumber(phoneNumberAsString);
                PhoneNumber phoneNumber = this.phoneNumberService.create(phoneNumberCreationModel);
                phoneNumber.setUserPersonalDetails(userPersonalDetails);
                userPersonalDetails.addPhoneNumber(phoneNumber);
            }
        }

        return this.userPersonalDetailsRepository.save(userPersonalDetails);
    }
}
