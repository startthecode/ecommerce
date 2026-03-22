package org.authetication.ecommerce.controllers;

import jakarta.validation.Valid;
import org.authetication.ecommerce.dto.request.ChangeRoleRequest;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.Mapping.ResponseMapper;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.services.RoleServices;
import org.authetication.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    UserService userService;
    RoleServices roleServices;
    public RolesController(UserService userService,RoleServices roleServices) {
        this.userService = userService;
        this.roleServices =roleServices;

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<ApiBaseResponse<String>> changeRole(@Valid @RequestBody ChangeRoleRequest bdy) {
        return ResponseEntity.ok(ResponseMapper.success(this.userService.updateUserRole(bdy.userID(), bdy.newRoleID()),
                "Roles has been updated"));
    }

    @GetMapping("/allroles")
    public ResponseEntity<ApiBaseResponse<List<Map<String,Object>>>> getAllRoles(){
        List<RolesEntity> allRoles = this.roleServices.getAllRoles();
        List<Map<String,Object>> resp = new ArrayList<>();
        allRoles.stream().forEach((e)->{
            Map<String,Object> newMap = new HashMap<>();
            newMap.put("Role_ID",e.getRoleId());
            newMap.put("Role_name",e.getRole());
            resp.add(newMap);
        });
        return ResponseEntity.ok(ResponseMapper.success(resp,"All Roles"));
    }





}
