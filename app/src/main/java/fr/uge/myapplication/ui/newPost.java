package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import fr.uge.myapplication.R;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.GPS;
import fr.uge.myapplication.service.network.Httpservice;
import fr.uge.myapplication.ui.profile.ProfilePageActivity2;

public class newPost extends AppCompatActivity {

    TextInputEditText title;
    TextInputEditText content;
    Httpservice http;
    String pos;

    void initi(){
        title = findViewById(R.id.newtitlePost);
        content = findViewById(R.id.newcontentpost);
        http =Httpservice.getinstance();
        http.setCtx(newPost.this);
        if(
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION )!= PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
            return;
        }

        GPS gps = new GPS(this);
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);
            String cityName = addresses.get(0).getAddressLine(0);
            String countryName = addresses.get(0).getCountryName();
            pos = countryName+","+cityName.split(",")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initi();
        Button b = findViewById(R.id.createPost);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject js = new JSONObject();
                final boolean[] good = {false};
                try {
                    js.put("title",title.getEditableText().toString());
                    js.put("content",content.getEditableText().toString());
                    js.put("pos",pos);
                    System.out.println(http.getCokkie());
                    http.postRequest("http://192.168.1.114:8080/user/createPost", js, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.split(",")[0].equals("200")){
                                good[0] = true;
                                newPost.this.finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(newPost.this,"can't creat post",Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                initi();
            }
        }
    }

}