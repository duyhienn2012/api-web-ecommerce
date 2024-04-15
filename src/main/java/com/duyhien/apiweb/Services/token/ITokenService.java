package com.duyhien.apiweb.Services.token;

import com.duyhien.apiweb.Entities.TokenEntity;
import com.duyhien.apiweb.Entities.UserEntity;
import org.springframework.stereotype.Service;

@Service

public interface ITokenService {
    TokenEntity addToken(UserEntity user, String token, boolean isMobileDevice);
    TokenEntity refreshToken(String refreshToken, UserEntity user) throws Exception;
}
