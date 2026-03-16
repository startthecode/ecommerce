package org.authetication.ecommerce.repository;

import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByRole(String role);
    RolesEntity findByRoleId(int roleId);
}
