package com.ijse.dbms.service;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.LoginDto;

import lombok.Getter;
import lombok.Setter;


@Service
public class UserTypeServiceImpl implements UserTypeService {
    private LoginDto logindto;

    @Override
    public LoginDto getLoginDto() {
         return this.logindto;
    }

    @Override
    public void setLoginDto(LoginDto loginDto) {
         this.logindto =loginDto;
    }

}
