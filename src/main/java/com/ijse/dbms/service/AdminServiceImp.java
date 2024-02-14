package com.ijse.dbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.PasswordDto;
import com.ijse.dbms.entity.Admin;
import com.ijse.dbms.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin createAdmin(Admin admin) {
         return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
         return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin changeAdminPassword(Long id, PasswordDto userPwdDto) {
        Admin existingAdmin =adminRepository.findById(id).orElse(null);
        if(existingAdmin !=null) {
            existingAdmin.setPassword(userPwdDto.getPassword());
            return adminRepository.save(existingAdmin);
        } else {
           return null;
        }
    }

    @Override
    public Admin findByAdminName(String name) {
        return adminRepository.findByName(name).orElse(null);
    }

    @Override
    public Long findIdByAdminName(String name) {
        return adminRepository.findIdByUserName(name);
    }
    
}
