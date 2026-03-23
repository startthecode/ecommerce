package org.authetication.ecommerce.controllers;


import jakarta.validation.Valid;
import org.authetication.ecommerce.Mapping.UserDtlMapper;
import org.authetication.ecommerce.Mapping.UserFillDtlMapper;
import org.authetication.ecommerce.dto.request.UserDTLCreate;
import org.authetication.ecommerce.dto.request.UserDTlUpdate;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.UserDTLAllResponse;
import org.authetication.ecommerce.dto.response.UserDtlReponse;
import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/details")
    public  ResponseEntity<ApiBaseResponse<UserDTLAllResponse>> getUserDetails(){
        Map<String,Object> info = this.userservice.getUserDetails();
        return  ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "ok",
                        UserFillDtlMapper
                                .toResponse(
                                        (UserEntity) info.get("user"),
                                        (UserDtlEntity) info.get("userDtl")
                                )));
    }

    @PutMapping("/details")
    public ResponseEntity<ApiBaseResponse<UserDtlReponse>> updateUserDtl(@Valid @RequestBody UserDTlUpdate body){
        Map<String,Object> info = this.userservice.updateUser(body);
        return  ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "ok",
                        UserDtlMapper
                                .toResponse(
                                        (UserEntity) info.get("user"),
                                        (UserDtlEntity) info.get("userDtl")
                                        )));
    }

    @PostMapping("/details")
    public ResponseEntity<ApiBaseResponse<UserDtlReponse>> createUserDtl(@Valid @RequestBody UserDTLCreate body){
        System.out.println(body);
        Map<String,Object> info = this.userservice.createUser(body);
        return  ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "ok",
                        UserDtlMapper
                                .toResponse(
                                        (UserEntity) info.get("user"),
                                        (UserDtlEntity) info.get("userDtl")
                                )));
    }

}
