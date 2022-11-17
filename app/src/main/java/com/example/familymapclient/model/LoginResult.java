package com.example.familymapclient.model;

public class LoginResult extends Result{

    private String authtoken;
    private String username;
    private String personID;

    public LoginResult(String message){
        super(message);
    }

    public LoginResult(String authtoken, String username, String personID){
        super(null);
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public String getAuthtoken(){
        return authtoken;
    }

    public String getPersonID(){
        return personID;
    }
}
