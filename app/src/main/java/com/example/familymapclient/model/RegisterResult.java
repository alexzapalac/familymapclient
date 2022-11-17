package com.example.familymapclient.model;

public class RegisterResult extends Result{

    private String authtoken;
    private String username;
    private String personID;

    public RegisterResult(String message){
        super(message);
    }

    public RegisterResult(String authtoken, String username, String personID){
        super(null);
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public String getUsername(){
        return username;
    }

    public String getAuthtoken(){
        return authtoken;
    }

    public String getPersonID(){
        return personID;
    }
}
