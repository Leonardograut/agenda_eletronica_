package com.projeto.jwt.enums;

public enum UserRole {

    USER("USER");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
