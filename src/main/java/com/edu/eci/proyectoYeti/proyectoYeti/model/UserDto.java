package com.edu.eci.proyectoYeti.proyectoYeti.model;

import java.util.ArrayList;

public class UserDto {

    private String name;
    private String mail;
    private String phone;
    private byte age;
    private String nationality;
    private char sex;
    private String password;
    private char role;
    private ArrayList<String> packages;

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public byte getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public char getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getPackages(){return packages;}

    public char getRole() {
        return role;
    }
}
