package com.app.model;

import org.springframework.data.cassandra.mapping.Table;

import java.nio.ByteBuffer;

/**
 * Created by cassandra on 6/5/14.
 */

@Table
public class UserInfo extends AbstractEntity {

    private String userName;
    private String email;
    private String password;
    private ByteBuffer image;

    public UserInfo() {
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ByteBuffer getImage() {
        return image;
    }

    public void setImage(ByteBuffer image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image=" + image +
                '}';
    }
}
