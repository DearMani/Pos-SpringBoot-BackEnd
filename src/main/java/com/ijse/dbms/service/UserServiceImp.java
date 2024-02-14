package com.ijse.dbms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.PasswordDto;
import com.ijse.dbms.entity.User;
import com.ijse.dbms.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changeUserPassword(Long id, PasswordDto userPwdDto) {
          User existingUser =userRepository.findById(id).orElse(null);
          if(existingUser !=null) {
              existingUser.setPassword(userPwdDto.getPassword());
              return userRepository.save(existingUser);
          } else {
             return null;
          }
    }

    @Override
    public User findByUserName(String userName) {

     return userRepository.findByName(userName).orElse(null);

    }

    @Override
    public Long findIdByUserName(String name) {
          return userRepository.findIdByUserName(name);
    }
    
}