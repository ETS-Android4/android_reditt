package fr.uge.myapplication.service.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Httpservice {
    private final static Httpservice instance = new Httpservice();
    private Context ctx;
    private RequestQueue queue;


    private Httpservice(){

    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public static Httpservice getinstance(){
        return instance;
    }

    public HttpResponseObject postRequest(String url, JSONObject jsonBody){
        final HttpResponseObject[] resp = new HttpResponseObject[1];
        final String requestBody = jsonBody.toString();
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
}
