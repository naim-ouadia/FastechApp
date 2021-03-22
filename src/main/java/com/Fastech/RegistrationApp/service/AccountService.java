package com.Fastech.RegistrationApp.service;

import com.Fastech.RegistrationApp.entities.AppRole;
import com.Fastech.RegistrationApp.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String userName, String password, String ConfirmedPassword);

    public AppRole save(AppRole role);

    public AppUser loadUserByUserName(String userName);

    public void addRoleToUser(String userName, String roleName);
}
