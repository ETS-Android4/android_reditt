package fr.uge.myapplication.service;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import fr.uge.myapplication.service.network.HttpResponseObject;
import fr.uge.myapplication.service.network.Httpservice;

public class AuthService {

    Context ctx;
    String url ="http://192.168.250.36:8080/" ;

    public AuthService(Context ctx) {
        this.ctx = ctx;
        Httpservice.getinstance().setCtx(ctx);
    }

    public void login(String username, String passwd, Response.Listener listener,Response.ErrorListener error){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", username);
            jsonBody.put("pwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        final HttpResponseObject[] responseObject = new HttpResponseObject[1];
        http.postRequest(url + "login", jsonBody, listener,error);
    }

    public void signup(String username,String passwd,String eamil, Response.Listener listener,Response.ErrorListener error){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", username);
            jsonBody.put("email", eamil);
            jsonBody.put("pwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.postRequest(url+"/signup",jsonBody,listener,error);
    }

    public void logout(Response.Listener listener,Response.ErrorListener error){
        Httpservice http = Httpservice.getinstance();

        http.postRequest(url+"/user/logout",null,listener,error);
    }

    public void modifypasswd(String old,String newpwd, Response.Listener listener,Response.ErrorListener error){
        Httpservice http = Httpservice.getinstance();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", old);
            jsonBody.put("pwd", newpwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.postRequest(url+"/user/modifyPwd",jsonBody,listener,error);

    }
}
