package com.refinninterview.demo.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/admin")
public class AdminController {


    @PostMapping("/login")
    void login() {
    }

    @PostMapping("/logout")
    void logout() {
    }
}
