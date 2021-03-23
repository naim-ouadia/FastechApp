package com.Fastech.RegistrationApp.serviceImpl;

import com.Fastech.RegistrationApp.DAO.AppRoleRepository;
import com.Fastech.RegistrationApp.DAO.AppUserRepository;
import com.Fastech.RegistrationApp.entities.AppRole;
import com.Fastech.RegistrationApp.entities.AppUser;
import com.Fastech.RegistrationApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(String emailAdress, String firstName, String lastName, String password, String ConfirmedPassword) {
        AppUser user = appUserRepository.findByEmailAdress(emailAdress);
        if (user != null) throw new RuntimeException("user already exixts");
        if (!password.equals(ConfirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setEmailAdress(emailAdress);
        appUser.setActived(true);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(emailAdress, "USER");
        return appUser;
    }

    @Override
    public AppUser updateUser(long id, AppUser appUser) {
        AppUser user1 = appUserRepository.findById(id).get();
        if (user1 == null) {
            return null;
        } else {
            appUser.setId(id);
            appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            appUser.setActived(true);
            appUser.setRoles(user1.getRoles());
            appUserRepository.save(appUser);
            return appUser;
        }
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByEmailAdress(String emailAdress) {
        return appUserRepository.findByEmailAdress(emailAdress);
    }

    @Override
    public void addRoleToUser(String emailAdress, String roleName) {
        AppUser appUser = appUserRepository.findByEmailAdress(emailAdress);
        AppRole appRole = appRoleRepository.findByroleName(roleName);
        appUser.getRoles().add(appRole);

    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}
