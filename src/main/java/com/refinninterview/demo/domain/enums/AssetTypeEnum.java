package com.refinninterview.demo.domain.enums;

public enum AssetTypeEnum {
    CONDO("condo"),
    HOUSE("house"),
    TOWN_HOUSE("townhouse"),
    LAND("land");

    private final String value;

    AssetTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
