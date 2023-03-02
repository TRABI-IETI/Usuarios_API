package com.edu.eci.proyectoYeti.proyectoYeti.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_collection")
public class User {

    @Id
    private String id;
    private String name;
    private String mail;
    private String phone;
    private byte age;
    private String nationality;
    private char sex;

    public User(String id, String name, String mail, String phone, byte age, String nationality, char sex) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.age = age;
        this.nationality = nationality;
        this.sex = sex;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void update(User user){
        this.name = user.getName();
        this.mail = user.getMail();
        this.phone = user.getPhone();
        this.sex = user.getSex();
    }
}
