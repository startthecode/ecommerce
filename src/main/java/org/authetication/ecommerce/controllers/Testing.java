package org.authetication.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing")
public class Testing {

    @GetMapping("/unprotected")
    public String test(){
        return "Testing API is working fine";
    }

    @GetMapping("/protected")
    public String protectedR(){
        return "Testing API is working fine for proteted routes";
    }


}
