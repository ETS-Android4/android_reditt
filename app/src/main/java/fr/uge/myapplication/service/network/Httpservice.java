package fr.uge.myapplication.service.network;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Httpservice {
    private final static Httpservice instance = new Httpservice();
    private Context ctx;
    private RequestQueue queue;
    private String resps ;
    String cokkie ="none";
    String username ="notconnected";

    private Httpservice(){
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;

        queue = Volley.newRequestQueue(ctx);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCokkie() {
        return cokkie;
    }

    public void setCokkie(String cokkie) {
        this.cokkie = cokkie;
    }

    public static Httpservice getinstance(){
        return instance;
    }


    public void postRequest(String url, JSONObject jsonBody , Response.Listener listener,Response.ErrorListener error){
        if(jsonBody ==null){
            jsonBody = new JSONObject();
        }
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
            public Map<String, String> getHeaders() throws AuthFailureError {

                if(cokkie.equals("none"))
                    return super.getHeaders();
                Map<String,String> header  = new HashMap<String, String>();
                header.put("Cookie",cokkie);

                return header;
            }



            @Override
            protected Response <String> parseNetworkResponse(NetworkResponse response) {
                String parsed = String.valueOf(response.statusCode);
                String cookie = "" ;
                try {
                    if(response.statusCode == 200) {
                        try {
                            for (Header h:response.allHeaders) {
                                if(h.getName().equals("Set-Cookie")){
                                    cookie = h.getValue().split(";")[0];


                                }
                            }
                        }catch (Exception e){

                        }
                        parsed += "," + new String(response.data, HttpHeaderParser.parseCharset(response.headers))+","+ cookie ;
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
    public void deleteRequest(String url, Response.Listener listener,Response.ErrorListener error){

        StringRequest request = new StringRequest(Request.Method.DELETE, url ,listener,error){



            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                if(cokkie.equals("none"))
                    return super.getHeaders();
                Map<String,String> header  = new HashMap<String, String>();
                header.put("Cookie",cokkie);

                return header;
            }



            @Override
            protected Response <String> parseNetworkResponse(NetworkResponse response) {
                String parsed = String.valueOf(response.statusCode);
                String cookie = "" ;
                try {
                    if(response.statusCode == 200) {
                        try {
                            for (Header h:response.allHeaders) {
                                if(h.getName().equals("Set-Cookie")){
                                    cookie = h.getValue().split(";")[0];


                                }
                            }
                        }catch (Exception e){

                        }
                        parsed += "," + new String(response.data, HttpHeaderParser.parseCharset(response.headers))+","+ cookie ;
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



    public HttpResponseObject getRequest(String url , Response.Listener listener,Response.ErrorListener error){
        final HttpResponseObject[] resp = new HttpResponseObject[1];
        StringRequest request = new StringRequest(Request.Method.POST, url ,listener, error
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

    public void getJsonArray(String url,Response.Listener listener,Response.ErrorListener errorListener){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,listener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                if(cokkie.equals("none"))
                    return super.getHeaders();
                Map<String,String> header  = new HashMap<String, String>();
                header.put("Cookie",cokkie);

                return header;
            }
        };
        queue.add(request);
    }
    public void getJsonobj(String url,JSONObject js,Response.Listener listener,Response.ErrorListener errorListener){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, js,listener,errorListener){
        };
        queue.add(request);
    }

}
