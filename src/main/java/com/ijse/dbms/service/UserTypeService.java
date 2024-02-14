package com.ijse.dbms.service;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.LoginDto;

@Service
public interface UserTypeService {
    LoginDto getLoginDto();
    void setLoginDto(LoginDto loginDto);
    
}
