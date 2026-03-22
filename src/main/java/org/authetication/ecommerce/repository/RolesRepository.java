package org.authetication.ecommerce.repository;

import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByRole(String role);
    Optional<RolesEntity> findByRoleId(int roleId);
}
