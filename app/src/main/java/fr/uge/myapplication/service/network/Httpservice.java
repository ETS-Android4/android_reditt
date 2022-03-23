package fr.uge.myapplication.service.network;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Httpservice {
    private final static Httpservice instance = new Httpservice();
    private Context ctx;
    private RequestQueue queue;
    private String resps ;


    private Httpservice(){
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
        queue = Volley.newRequestQueue(ctx);
    }

    public static Httpservice getinstance(){
        return instance;
    }


    public void postRequest(String url, JSONObject jsonBody , Response.Listener listener,Response.ErrorListener error){
        final HttpResponseObject[] resp = new HttpResponseObject[1];
        final String requestBody = jsonBody.toString();
        StringRequest request = new StringRequest(Request.Method.POST, url ,listener,error){

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }


            @Override
            protected Response <String> parseNetworkResponse(NetworkResponse response) {
                String parsed = String.valueOf(response.statusCode);
                try {
                    if(response.statusCode == 200) {
                        parsed += "," + new String(response.data, HttpHeaderParser.parseCharset(response.headers)) ;
                    }
                    //parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }

                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));

            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                System.out.println("error");
                return super.parseNetworkError(volleyError);
            }
        };
        queue.add(request);
    }

    public HttpResponseObject getRequest(String url){
        final HttpResponseObject[] resp = new HttpResponseObject[1];
        StringRequest request = new StringRequest(Request.Method.POST, url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resp[0] = new HttpResponseObject(response.split(",")[0],response.split(",")[1]);
                        System.out.println(response);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }
        ){
            @Override
            protected Response < String > parseNetworkResponse(NetworkResponse response) {

                String parsed = String.valueOf(response.statusCode);
                try {
                    if(response.statusCode == 200) {
                        parsed += "," + new String(response.data, HttpHeaderParser.parseCharset(response.headers)) ;
                    }
                    //parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }

                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));

            }

        };
        queue.add(request);
        return resp[0];
    }

    public JSONObject getjsonObject(String url){
        final HttpResponseObject[] resp = new HttpResponseObject[1];
        final JSONObject[] jsonObject = {null};
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonObject[0] = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };

        queue.add(request);
        return jsonObject[0];
    }

    public String getResps() {
        return resps;
    }
}
