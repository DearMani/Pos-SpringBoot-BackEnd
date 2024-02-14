package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.PasswordDto;
import com.ijse.dbms.entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User changeUserPassword(Long id,PasswordDto userPwdDto);
    User findByUserName(String name);
    Long findIdByUserName(String name);
}
