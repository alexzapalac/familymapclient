package com.example.familymapclient.model;

public class Result {

    private final String message;

    Result (String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
