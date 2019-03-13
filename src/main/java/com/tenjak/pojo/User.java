package com.tenjak.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class User implements Serializable{
    @Excel(name = "keyid")
    private String  keyid;
    @Excel(name = "用户昵称")
    private String  username;
    @Excel(name = "用户密码")
    private String  password;
    @Excel(name = "用户真实名称")
    private String  realname;

    @Override
    public String toString() {
        return "User{" +
                "keyid='" + keyid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
