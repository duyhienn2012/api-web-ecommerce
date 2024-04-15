package com.duyhien.apiweb.Repositories;

import com.duyhien.apiweb.Entities.RoleEnity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEnity, Long> {
}
