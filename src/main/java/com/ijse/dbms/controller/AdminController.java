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
import com.ijse.dbms.entity.Admin;
import com.ijse.dbms.service.AdminService;

@RestController
@CrossOrigin(origins = "*" )
public class AdminController {
    
    @Autowired
    AdminService adminService;
     
      @GetMapping("/admins")
    public List<Admin> getAllUsers(){
          return adminService.getAllAdmins();
    }

    @PostMapping("/admins")
    public Admin createUser(@RequestBody Admin user) {
         return adminService.createAdmin(user);
    }

    @GetMapping("/admins/{id}")
    public Admin getUserById(@PathVariable Long id){
         return adminService.getAdminById(id);
    }

    @PutMapping("/admins/{id}/change-password")
    public ResponseEntity<Admin> changePassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
         return ResponseEntity.ok().body(adminService.changeAdminPassword(id, passwordDto));
    }

    @GetMapping("/admins/{UserName}")
    public Admin getUserByName(@PathVariable String name){
          return adminService.findByAdminName(name);
    }

    @GetMapping("/admins/{userName}/get-id")
    public Long getIdUserByName(@PathVariable String userName){
          return adminService.findIdByAdminName(userName);
    }

}
