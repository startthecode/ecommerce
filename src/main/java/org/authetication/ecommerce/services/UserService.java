package org.authetication.ecommerce.services;

import org.authetication.ecommerce.dto.request.UserDTLCreate;
import org.authetication.ecommerce.dto.request.UserDTlUpdate;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserDTLRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.authetication.ecommerce.services.imp.UserPrincipleImp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    UserRepository userRepo;
    UserDTLRepository userDTLRepo;
    RolesRepository rolesRepo;

    public UserService(UserDTLRepository userDTLRepo, UserRepository userRepo,RolesRepository rolesRepo) {
        this.userDTLRepo = userDTLRepo;
        this.userRepo = userRepo;
        this.rolesRepo = rolesRepo;
    }

    public Map<String,Object> createUser(UserDTLCreate req){
        Map<String,Object> data = new HashMap<>();
        UserPrincipleImp principal =
                (UserPrincipleImp) Objects.requireNonNull(SecurityContextHolder
                                .getContext()
                                .getAuthentication())
                        .getPrincipal();
//        assert principal != null 💕 learn this;
        UserEntity user = principal.getUser();
        UserDtlEntity newData = new UserDtlEntity();
        newData.setAddress(req.address());
        newData.setPincode(req.pincode());
        newData.setUser(user);
        userDTLRepo.save(newData);
        data.put("user",user);
        data.put("userDtl",newData);
        return data;
    }

    public Map<String,Object> updateUser(UserDTlUpdate req){
        Map<String,Object>  data = new HashMap<>();
        UserPrincipleImp principal =
                (UserPrincipleImp) Objects.requireNonNull(SecurityContextHolder
                                .getContext()
                                .getAuthentication())
                        .getPrincipal();
        UserEntity user = principal.getUser();
        UserDtlEntity newData = userDTLRepo.findByUser(user).orElseThrow(()-> new GenericException("User not found"));
        if(req.address() != null){
            newData.setAddress(req.address());
        }
        if(req.pincode() != null){
            newData.setPincode(req.pincode());
        }
        newData.setUser(user);
        userDTLRepo.save(newData);
        data.put("user",user);
        data.put("userDtl",newData);
        return data;
    }

    public Map<String,Object> getUserDetails(){
        UserPrincipleImp userPrinciple = (UserPrincipleImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userPrinciple.getUser();
        UserDtlEntity dtl = user.getUserdetail();
        Map<String,Object>  data = new HashMap<>();
        data.put("user",user);
        data.put("userDtl",dtl);
        return  data;
    }

    public String updateUserRole(Long userID, int newRoleID){
        UserEntity user = userRepo.findAllByUserid(userID).orElseThrow(()->new GenericException("Usern not found"));
        RolesEntity newRole  = rolesRepo.findByRoleId(newRoleID).orElseThrow(()->new GenericException("invalid role id"));
        String oldRole = user.getRole().getRole();
        user.setRole(newRole);
        userRepo.save(user);
        return  "user roles has been changed from " + oldRole + " to "+ newRole.getRole();
    }

    public UserEntity getCurrentUser(){
        UserPrincipleImp principal =
                (UserPrincipleImp) Objects.requireNonNull(SecurityContextHolder
                                .getContext()
                                .getAuthentication())
                        .getPrincipal();
        assert principal != null;
        Long userId = principal.getUser().getuserid();
        return userRepo.findAllByUserid(userId)
                .orElseThrow(() -> new GenericException("User not found"));
    }
}
