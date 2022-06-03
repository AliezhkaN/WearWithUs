package com.workWithUs.model.entity;

public enum Type {
    T_SHIRT("T-SHIRT"),SWEATSHIRT("SWEATSHIRT");

    private String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
