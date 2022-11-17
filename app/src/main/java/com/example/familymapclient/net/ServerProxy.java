package com.example.familymapclient.net;

import com.example.familymapclient.model.LoginResult;
import com.example.familymapclient.model.RegisterResult;
import com.example.familymapclient.utils.Deserializer;

import java.io.*;
import java.net.*;

public class ServerProxy {

    private final String serverHost;
    private final String serverPort;

    public ServerProxy(String serverHost, String serverPort){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public RegisterResult registerUser (String username, String password, String email,
                                        String firstName, String lastName, String gender){
        String requestData = "{\"username\":\"" + username + "\", \"password\":\"" + password +
                "\", \"email\":\"" + email + "\", \"firstName\":\"" + firstName +
                "\", \"lastName\":\"" + lastName + "\", \"gender\":\"" + gender + "\"}";

        return (RegisterResult)serverPost(requestData, "register", RegisterResult.class);
    }

    public LoginResult loginUser(String username, String password){
        String requestData = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
        return (LoginResult) serverPost(requestData, "login", LoginResult.class);
    }

    public String getAllUserPersons(String authtoken) {
        return serverGet(authtoken, "person");
    }

    public String getAllUserEvents(String authtoken) {
        return serverGet(authtoken, "event");
    }

    private Object serverPost(String reqData, String apiName, Class resultClass) {
        Object result = null;

        try {
            //HttpURLConnection http = openHttpURLConnection("user/" + apiName);
            //http.setRequestMethod("POST");

            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/" + apiName);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");



            // There is a request body
            http.setDoOutput(true);

            http.connect();

            OutputStream reqBody = http.getOutputStream();

            writeString(reqData, reqBody);

            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {  //TODO: Why does response code = -1
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                result = new Deserializer().deserialize(respData, resultClass);
            } else {
                System.out.println("serverPost ERROR: " + http.getResponseMessage());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String serverGet(String authtoken, String apiName) {
        String retrieved = null;

        try {
            HttpURLConnection http = openHttpURLConnection(apiName);
            http.setRequestMethod("GET");

            // There is no request body
            http.setDoOutput(false);

            http.addRequestProperty("Authorization", authtoken);

            // Connect to the server and send the HTTP request
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                retrieved = readString(respBody);
            } else {
                System.out.println("serverGet ERROR: " + http.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retrieved;
    }

    private HttpURLConnection openHttpURLConnection(String apiName) throws IOException {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/" + apiName);
            return (HttpURLConnection)url.openConnection();
        } catch(Exception e) {
            throw e;
        }
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }

        return sb.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
