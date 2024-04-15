package com.duyhien.apiweb.Services.user;

import com.duyhien.apiweb.DTO.UserDTO;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;
import com.duyhien.apiweb.Exceptions.InvalidPasswordException;
import com.duyhien.apiweb.DTO.UpdateUserDTO;
import com.duyhien.apiweb.DTO.UserLoginDTO;
import com.duyhien.apiweb.Entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    UserEntity createUser(UserDTO userDTO) throws Exception;
    String login(UserLoginDTO userLoginDT) throws Exception;
    UserEntity getUserDetailsFromToken(String token) throws Exception;
    UserEntity getUserDetailsFromRefreshToken(String token) throws Exception;
    UserEntity updateUser(Long userId, UpdateUserDTO updatedUserDTO) throws Exception;

    Page<UserEntity> findAll(String keyword, Pageable pageable) throws Exception;
    void resetPassword(Long userId, String newPassword)
            throws InvalidPasswordException, DataNotFoundException;
    public void blockOrEnable(Long userId, Boolean active) throws DataNotFoundException;
}
