package com.Fastech.RegistrationApp.DAO;

import com.Fastech.RegistrationApp.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByEmailAdress(String emailAdress);
}
