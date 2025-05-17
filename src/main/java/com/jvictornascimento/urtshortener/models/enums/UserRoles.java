package com.jvictornascimento.urtshortener.models.enums;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private String roles;

    UserRoles(String role){
        this.roles = role;
    }
    public String getRoles(){
        return roles;
    }
}
