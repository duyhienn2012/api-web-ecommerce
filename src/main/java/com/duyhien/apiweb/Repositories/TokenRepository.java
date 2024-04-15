package com.duyhien.apiweb.Repositories;


import com.duyhien.apiweb.Entities.TokenEntity;
import com.duyhien.apiweb.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    List<TokenEntity> findByUser(UserEntity user);
    TokenEntity findByToken(String token);
    TokenEntity findByRefreshToken(String token);
}

