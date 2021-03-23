package com.Fastech.RegistrationApp.web;

import com.Fastech.RegistrationApp.entities.AppUser;
import com.Fastech.RegistrationApp.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/singUp")
    public AppUser singUp(@RequestBody UserForm userForm) {
        return accountService.saveUser(userForm.getEmailAdress(), userForm.getFirstName(), userForm.getLastName(), userForm.getPassword(), userForm.getConfirmedPassword());
    }

    @PutMapping("/upDateUser/{id}")
    public AppUser updateUser(@PathVariable long id, @RequestBody AppUser appUser) {
        return accountService.updateUser(id, appUser);
    }

    @GetMapping("/AllUsers")
    public List<AppUser> getAllUsers() {
        return accountService.getAllUsers();
    }
}


@Data
class UserForm {
    private String emailAdress;
    private String firstName;
    private String lastName;
    private String password;
    private String ConfirmedPassword;
}
