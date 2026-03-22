package org.authetication.ecommerce.services;

import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {
    RolesRepository rolesRepository;

    public RoleServices(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<RolesEntity> getAllRoles(){
        List<RolesEntity> allRoles = rolesRepository.findAll();
        return allRoles;
    }
}
