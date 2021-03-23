package com.Fastech.RegistrationApp.service;

import com.Fastech.RegistrationApp.entities.AppRole;
import com.Fastech.RegistrationApp.entities.AppUser;

import java.util.List;

public interface AccountService {

    public AppUser saveUser(String emailAdress, String firstName, String lastName, String password, String ConfirmedPassword);

    public AppRole save(AppRole role);

    public AppUser updateUser(long id, AppUser appUser);

    public AppUser loadUserByEmailAdress(String emailAdress);

    public void addRoleToUser(String emailAdress, String roleName);

    public List<AppUser> getAllUsers();
}
