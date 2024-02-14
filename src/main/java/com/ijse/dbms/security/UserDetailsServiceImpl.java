package com.ijse.dbms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.LoginDto;
import com.ijse.dbms.entity.Admin;
import com.ijse.dbms.entity.SuperUser;
import com.ijse.dbms.entity.User;
import com.ijse.dbms.repository.AdminRepository;
import com.ijse.dbms.repository.UserRepository;
import com.ijse.dbms.service.UserTypeService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserTypeService userTypeService;

    @Override
    public UserDetails loadUserByUsername(String username) {
       LoginDto dto =userTypeService.getLoginDto();
        String userType =dto.getUsertype();

        // User customer = userRepository.findByUserName(username);
        // SuperUser user =customer;

        // if( user == null ) {
        //     throw new UsernameNotFoundException("User is not found with the username");
        // }

        // return org.springframework.security.core.userdetails.User.builder()
        //     .username(user.getUserName())
        //     .password(user.getPassword())
        //     .build();

            if(userType.equals("CUSTOMER")){
            System.out.println(userType);
            System.out.println(username);
            
          User customer = userRepository.findByUserName(username);
          System.out.println("//////////////////////////////////////////////");
           return org.springframework.security.core.userdetails.User.builder()
            .username(customer.getUserName())
            .password(customer.getPassword())
            .build();

        } else if(userType.equals("ADMIN")) {

            System.out.println(userType);
            System.out.println(username);

          Admin user = adminRepository.findByUserName(username);
           return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .build();
        } 
          else {
                 throw new UsernameNotFoundException("User is not found with the username");
          }

    }

     public UserDetails loadUserByUsername(String username , String userType)
     {
        //    System.out.println(userType);
        //     System.out.println(username);
        
          if(userType.equals("CUSTOMER")){
            System.out.println(userType);
            System.out.println(username);
            
          User customer = userRepository.findByUserName(username);
          System.out.println("//////////////////////////////////////////////");
           return org.springframework.security.core.userdetails.User.builder()
            .username(customer.getUserName())
            .password(customer.getPassword())
            .build();

        } else if(userType.equals("ADMIN")) {

            System.out.println(userType);
            System.out.println(username);

          Admin user = adminRepository.findByUserName(username);
           return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .build();
        } 
          else {
                 throw new UsernameNotFoundException("User is not found with the username");
          }
      
    }

}
