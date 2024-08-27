package com.hieu.demotestspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/withdraw")
    public String withDrawMoney(String accountNumber, Long amount){
        return "success";
    }
}
