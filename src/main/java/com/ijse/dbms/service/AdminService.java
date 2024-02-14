package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.PasswordDto;
import com.ijse.dbms.entity.Admin;

@Service
public interface AdminService {
    List<Admin> getAllAdmins();
    Admin createAdmin(Admin user);
    Admin getAdminById(Long id);
    Admin changeAdminPassword(Long id,PasswordDto userPwdDto);
    Admin findByAdminName(String name);
    Long findIdByAdminName(String name);
    
}
