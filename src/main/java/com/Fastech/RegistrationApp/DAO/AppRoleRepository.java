package com.Fastech.RegistrationApp.DAO;

import com.Fastech.RegistrationApp.entities.AppRole;
import com.Fastech.RegistrationApp.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByroleName(String roleName);
}
