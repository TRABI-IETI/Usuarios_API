package com.edu.eci.proyectoYeti.proyectoYeti.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private String passwordHash;
    private char role;

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    //    List<RoleEnum> roles;
    private ArrayList<String> packages = new ArrayList<>();


    public User(UserDto userDto){
        this.mail = userDto.getMail();
//        this.passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt() );
        this.passwordHash = userDto.getPassword();
        this.name = userDto.getName();
        this.phone = userDto.getPhone();
        this.age = userDto.getAge();
        this.nationality = userDto.getNationality();
        this.sex = userDto.getSex();
        this.packages = userDto.getPackages();
        this.role = userDto.getRole();
//        this.roles = new ArrayList<>( Collections.singleton( RoleEnum.USER ) );

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

    public ArrayList<String> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<String> packages) {
        packages = packages;
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

//    public List<RoleEnum> getRoles() {
//        return roles;
//    }

    public void update(UserDto userDto){
        this.mail = userDto.getMail();
        this.name = userDto.getName();
        this.phone = userDto.getPhone();
        this.age = userDto.getAge();
        this.nationality = userDto.getNationality();
        this.sex = userDto.getSex();
        this.packages = userDto.getPackages();
        this.passwordHash = userDto.getPassword();
        this.role = userDto.getRole();
//        if ( userDto.getPassword() != null ){
////            this.passwordHash = BCrypt.hashpw( userDto.getPassword(), BCrypt.gensalt() );
//        }

    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void addPackage(String paquete) {
        this.packages.add(paquete);
    }

    public void DeletePackage(String paquete) {
        this.packages.remove(paquete);
    }
}
