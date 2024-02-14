package com.ijse.dbms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String userName;
    private String password;
    private String usertype;
}
