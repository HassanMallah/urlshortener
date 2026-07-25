package com.hassan.urlshortener.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    ADMIN, USER;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static Roles fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}