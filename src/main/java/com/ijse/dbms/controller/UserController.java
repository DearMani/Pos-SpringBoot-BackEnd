package com.ijse.dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.dto.PasswordDto;
import com.ijse.dbms.entity.User;
import com.ijse.dbms.service.UserService;

@RestController
@CrossOrigin(origins = "*" )//allowing cross origin to all
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
          return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
         return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
         return userService.getUserById(id);
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
         return ResponseEntity.ok().body(userService.changeUserPassword(id, passwordDto));
    }

     @GetMapping("/users/{UserName}")
    public User getUserByName(@PathVariable String name){
          return userService.findByUserName(name);
    }

    @GetMapping("/users/{userName}/get-id")
    public Long getIdUserByName(@PathVariable String userName){
          return userService.findIdByUserName(userName);
    }

}