package com.emresahna.userservice.entity;

public enum UserType {
    CUSTOMER(1),
    SELLER(2);

    private final int value;

    UserType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
