package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.ERole;
import com.binar.pemesanantiketpesawat.model.Role;
import com.binar.pemesanantiketpesawat.repository.RoleRepository;
import com.binar.pemesanantiketpesawat.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String createNewRole() {
        roleRepository.saveAll(List.of(
                new Role(ERole.ROLE_ADMIN),
                new Role(ERole.ROLE_BUYER)
        ));
        return "role success added..!";
    }
}
