package com.qm.enums;

public enum Profile {

    DEV("dev"),TEST("test"),PROD("prod");
    private String code;

    Profile(String code) {
        this.code = code;
    }
}
