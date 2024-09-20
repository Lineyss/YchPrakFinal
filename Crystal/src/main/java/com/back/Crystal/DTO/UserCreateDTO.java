package com.back.Crystal.DTO;

import java.util.ArrayList;

public class UserCreateDTO {
    private String Login;
    private String Password;
    private Long IDRole;

    public UserCreateDTO(String login, String password, Long IDRole) {
        Login = login;
        Password = password;
        this.IDRole = IDRole;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getIDRole() {
        return IDRole;
    }

    public void setIDRole(Long IDRole) {
        this.IDRole = IDRole;
    }

}
