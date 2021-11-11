package com.xurong.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther xurong
 * @date 2021/11/3 - 19:17
 */
public class User implements Serializable {
    private Integer userId;
    private String userName;
    //如果改名为userName,该项目的其他地方不变，也能够将用户名插入数据库，因为Mysql数据库在Windows操作系统下
    //是不严格区分大小写的（Linux下严格区分）
    private String userAddress;
    private String userSex;
    private Date userBirthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }
}
