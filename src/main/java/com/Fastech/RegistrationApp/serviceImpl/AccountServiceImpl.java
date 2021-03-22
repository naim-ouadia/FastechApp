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

    public AppUser saveUser(String userName, String password, String ConfirmedPassword) {
        AppUser user = appUserRepository.findByuserName(userName);
        if (user != null) throw new RuntimeException("user already exixts");
        if (!password.equals(ConfirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUserName(userName);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(userName, "USER");
        return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByuserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = appUserRepository.findByuserName(userName);
        AppRole appRole = appRoleRepository.findByroleName(roleName);
        appUser.getRoles().add(appRole);

    }
}
