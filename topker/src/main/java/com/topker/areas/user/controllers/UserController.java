package com.topker.areas.user.controllers;

import com.topker.areas.user.models.bindingModels.RegistrationModel;
import com.topker.areas.user.models.bindingModels.UserPasswordChangeModel;
import com.topker.areas.user.service.BasicUserService;
import com.topker.areas.user.service.UserService;
import com.topker.configs.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final BasicUserService basicUserService;

    private final UserService userService;

    @Autowired
    public UserController(BasicUserService basicUserService, UserService userService) {
        this.basicUserService = basicUserService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult) {
        boolean isUsernameTaken = this.userService.isUsernameTaken(registrationModel.getUsername());
        if (isUsernameTaken) {
            bindingResult.addError(new FieldError("registrationModel", "username", "errorMessages.usernameTaken"));
        }

        if(bindingResult.hasErrors()){
            return "user/register";
        }

        this.basicUserService.register(registrationModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "user/login";
    }

    @GetMapping("/changePassword")
    public String getChangePasswordPage(@ModelAttribute UserPasswordChangeModel userPasswordChangeModel, Principal principal){
        String username = principal.getName();
        boolean hasPassword = this.userService.hasPassword(username);
        if (!hasPassword) {
            return "redirect:/";
        }

        return "user/password-change";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute UserPasswordChangeModel userPasswordChangeModel, BindingResult bindingResult, Principal principal){
        if (principal == null) {
            return "redirect:/";
        }

        String username = principal.getName();

        //check old pass
        boolean isValidUserInputOldPassword = this.userService.isValidPassword(userPasswordChangeModel.getOldPassword(), username);
        if (!isValidUserInputOldPassword) {
            bindingResult.addError(new FieldError("userPasswordChangeModel", "oldPassword", "errorMessages.oldPassword"));
        }

        if(bindingResult.hasErrors()){
            return "user/password-change";
        }

        this.userService.changePassword(userPasswordChangeModel, username);

        return "redirect:/";
    }
}
