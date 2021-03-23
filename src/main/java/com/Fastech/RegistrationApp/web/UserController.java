package com.Fastech.RegistrationApp.web;

import com.Fastech.RegistrationApp.entities.AppUser;
import com.Fastech.RegistrationApp.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/singUp")
    public AppUser singUp(@RequestBody UserForm userForm) {
        return accountService.saveUser(userForm.getUserName(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
}

@Data
class UserForm {
    private String userName;
    private String password;
    private String ConfirmedPassword;
}
