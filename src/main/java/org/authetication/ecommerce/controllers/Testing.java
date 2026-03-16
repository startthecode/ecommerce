package org.authetication.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class Testing {

    @GetMapping("")
    public String test(){
        return "Testing API is working fine";
    }


}
