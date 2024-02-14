package com.ijse.dbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.dto.LoginDto;
import com.ijse.dbms.entity.Admin;
import com.ijse.dbms.entity.User;
import com.ijse.dbms.repository.AdminRepository;
import com.ijse.dbms.repository.UserRepository;
import com.ijse.dbms.security.jwt.JwtUtils;
import com.ijse.dbms.service.AdminService;
import com.ijse.dbms.service.UserService;
import com.ijse.dbms.service.UserTypeService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        
        if(userRepository.existsByUserName(user.getUserName())) {
             return ResponseEntity.badRequest().body("UserName is already Use");
        } 

        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        User newUser =new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @PostMapping("/auth/admin-register")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        
        if(adminRepository.existsByUserName(user.getUserName())) {
             return ResponseEntity.badRequest().body("UserName is already Use");
        } 

        Admin newUser =new Admin();
        newUser.setUserName(user.getUserName());
        // newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(adminService.createAdmin(newUser));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
       String userType =loginDto.getUsertype(); 
       userTypeService.setLoginDto(loginDto);
       System.out.println("&&&&&&&&&&&&&&&&&&&&&"+userType);

        Authentication authentication =authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
        );
         System.out.println("&&&&&&&&&&&&&&&&&&&&&"+authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
          System.out.println("&&&&&&&&&&&&&&&&&&&&&"+userType);
        String jwt = jwtUtils.generateJwtToken(authentication, userType);
         return ResponseEntity.ok(jwt);

    }

}
