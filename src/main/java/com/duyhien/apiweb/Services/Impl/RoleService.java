package com.duyhien.apiweb.Services.Impl;

import com.duyhien.apiweb.Entities.RoleEnity;
import com.duyhien.apiweb.Repositories.RoleRepository;
import com.duyhien.apiweb.Services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<RoleEnity> getAllRoles() {
        return roleRepository.findAll();
    }
}
