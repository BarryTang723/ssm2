package cn.itcast.domain;

import java.util.List;
//用于登录,使用SpringSecurity
public class UserInfo {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private int status;
    private String statusStr;
    private String role;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public String getRole() {
        return role;
    }
}
