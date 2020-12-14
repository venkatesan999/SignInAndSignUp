package com.example.signinandsignup;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GetAndSet extends RealmObject {

    @PrimaryKey
    private int id;

    private String email;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
