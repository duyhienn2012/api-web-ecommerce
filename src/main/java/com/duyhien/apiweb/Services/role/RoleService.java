package com.duyhien.apiweb.Services.role;

import com.duyhien.apiweb.Entities.RoleEnity;
import com.duyhien.apiweb.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{
    private final RoleRepository roleRepository;
    @Override
    public List<RoleEnity> getAllRoles() {
        return roleRepository.findAll();
    }
}
