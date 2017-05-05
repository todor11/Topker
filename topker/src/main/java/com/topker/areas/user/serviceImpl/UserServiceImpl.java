package com.topker.areas.user.serviceImpl;

import com.topker.areas.role.services.RoleService;
import com.topker.areas.user.entities.User;
import com.topker.areas.user.models.bindingModels.UserPasswordChangeModel;
import com.topker.areas.user.repositories.UserRepository;
import com.topker.areas.user.service.UserService;
import com.topker.areas.userPersonalDetails.services.UserPersonalDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private RoleService roleService;

    private UserPersonalDetailsService userPersonalDetailsService;

    private ModelMapper modelMapper;


    @Override
    public boolean changePassword(UserPasswordChangeModel userPasswordChangeModel, String username) {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null || user.getPassword() == null) {
            return false;
        }

        if (!this.bCryptPasswordEncoder.matches(userPasswordChangeModel.getOldPassword(), user.getPassword())) {
            return false;
        }

        String newPassword = this.bCryptPasswordEncoder.encode(userPasswordChangeModel.getPassword());
        user.setPassword(newPassword);
         Object updatedUser = this.userRepository.save(user);
         if (updatedUser == null) {
             return false;
         }

         return true;
    }

    @Override
    public boolean isValidPassword(String userInputOldPassword, String username) {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null || user.getPassword() == null) {
            return false;
        }

        if (!this.bCryptPasswordEncoder.matches(userInputOldPassword, user.getPassword())) {
            return false;
        }

        return true;
    }

    @Override
    public boolean hasPassword(String username) {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null || user.getPassword() == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isUsernameTaken(String newUsername) {
        User user = this.userRepository.findOneByUsername(newUsername);
        if (user == null) {
            return false;
        }

        return true;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserPersonalDetailsService(UserPersonalDetailsService userPersonalDetailsService) {
        this.userPersonalDetailsService = userPersonalDetailsService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
