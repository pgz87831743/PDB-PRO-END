package com.project.app.enums;

public enum RoleEnum {
    ADMIN("管理员"),
    USER("普通用户"),
    ;


    private final String type;


    RoleEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
