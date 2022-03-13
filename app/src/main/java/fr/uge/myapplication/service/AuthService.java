package fr.uge.myapplication.service;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import fr.uge.myapplication.service.network.HttpResponseObject;
import fr.uge.myapplication.service.network.Httpservice;

public class AuthService {

    Context ctx;
    String url ="http://192.168.92.36:8080/" ;
    boolean connected = false;

    public AuthService(Context ctx) {
        this.ctx = ctx;

    }

    public void login(String username, String passwd){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", username);
            jsonBody.put("passwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        HttpResponseObject response = http.postRequest(url+"/login",jsonBody);
        if(response.getStatusCode().equals("200")){
            System.out.println("nice");
        }
    }

    public void signup(String username,String passwd){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", username);
            jsonBody.put("pwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpResponseObject response = http.postRequest(url+"/signup",jsonBody);
        if(response.getStatusCode().equals("200")){
            System.out.println("nice");
        }
    }

    public void logout(){
        Httpservice http = Httpservice.getinstance();

        HttpResponseObject response = http.postRequest(url+"/user/logout",null);
        if(response.getStatusCode().equals("200")){
            System.out.println("nice");
        }
    }

    public void modifypasswd(String old,String newpwd){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", old);
            jsonBody.put("pwd", newpwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpResponseObject response = http.postRequest(url+"/user/modifyPwd",jsonBody);
        if(response.getStatusCode().equals("200")){
            System.out.println("nice");
        }

    }
}
