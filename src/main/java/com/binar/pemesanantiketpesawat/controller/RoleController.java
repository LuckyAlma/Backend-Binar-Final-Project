package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public String addNewRole() {
        return roleService.createNewRole();
    }
}
