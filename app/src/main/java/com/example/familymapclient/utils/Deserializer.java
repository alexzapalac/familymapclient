package com.example.familymapclient.utils;

import com.google.gson.Gson;

public class Deserializer {

    public Object deserialize(String jsonStr, Class klass){
        return new Gson().fromJson(jsonStr, klass);
    }
}
