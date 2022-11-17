package com.example.familymapclient.utils;

import com.google.gson.Gson;

public class Serializer {

    public String serialize(Object obj){
        return new Gson().toJson(obj);
    }

}
