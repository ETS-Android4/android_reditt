package fr.uge.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {

    String connected = "not";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, PostList.class));
//        if(connected.equals("not")){
//            Intent intent = new Intent(MainActivity.this, Loginpage.class);
//            startActivity(intent);
//        }



    }
}