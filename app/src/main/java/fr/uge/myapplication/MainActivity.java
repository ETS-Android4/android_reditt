package fr.uge.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.model.Sru;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.HttpResponseObject;
import fr.uge.myapplication.service.network.Httpservice;
import fr.uge.myapplication.ui.Loginpage;
import fr.uge.myapplication.ui.PostList;
import fr.uge.myapplication.ui.RegisterActivity;
import fr.uge.myapplication.ui.newPost;
import fr.uge.myapplication.ui.profile.ProfilePageActivity2;

public class MainActivity extends AppCompatActivity {

    String connected = "not";

    Httpservice http;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final DrawerLayout drawerLayout = findViewById(R.id.navigation);
        login();
        startActivity(new Intent(MainActivity.this, PostList.class));

//        findViewById(R.id.loginpage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, Loginpage.class));
//            }
//        });
//        findViewById(R.id.signuppage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//            }
//        });

    }
    void login(){
        http = Httpservice.getinstance();
        http.setCtx(MainActivity.this);
        AuthService auth = new AuthService(http);
        auth.login("Ilyass", "test789", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.split(",")[0].equals("200")){
                    System.out.println(response.split(",").length);
                    System.out.println(response.split(",")[0]);
                    try {
                        Httpservice.getinstance().setUsername("Ilyass");
                        System.out.println(response.split(",")[2]);
                        http.setCokkie(response.split(",")[2]);
                    }catch (Exception e){

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        System.out.println("connected");
    }
}