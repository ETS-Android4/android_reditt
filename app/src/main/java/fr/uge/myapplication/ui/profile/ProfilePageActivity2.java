package fr.uge.myapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.model.Sru;
import fr.uge.myapplication.service.GPS;
import fr.uge.myapplication.service.network.Httpservice;
import fr.uge.myapplication.ui.PostAdapter;
import fr.uge.myapplication.ui.PostList;

public class ProfilePageActivity2 extends AppCompatActivity {


    private TextView Name;
    private Button Modify,ModifyProfile;
    private ImageButton back;
    private TextView Date;
    private TextView Country;
    private ImageView Profile;
    int a;
    RecyclerView recycle;

    private List<PC> pcs;

    PostAdapter postAdapter;

    public void getdata() {
        Httpservice.getinstance().setCtx(ProfilePageActivity2.this);
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject value = response.getJSONObject(i);
                        PC pc = new PC();
                        pc.setId(Long.parseLong(value.getString("id")));
                        pc.setTitle(value.getString("title"));
                        pc.setContent(value.getString("content"));

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        String dateInString = value.getString("date").split("\\.")[0];
                        java.util.Date date = formatter.parse(dateInString);
                        pc.setDate(date);
                        Sru user = new Sru();
                        user.setName(value.getJSONObject("author").getString("name"));
                        pc.setAuthor(user);
                        if (pc.getAuthor().getName().equals(Httpservice.getinstance().getUsername()))
                            pcs.add(pc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                postAdapter.setList(pcs);
                postAdapter.refresh();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    void inti(){
        Name = findViewById(R.id.name);
        Modify = findViewById(R.id.modifyprofile);
        Country = findViewById(R.id.pos);
        Profile = findViewById(R.id.profile);
        setavatar();
        ModifyProfile = findViewById(R.id.modifyPhoto);
        back = findViewById(R.id.back);
        Name.setText(Httpservice.getinstance().getUsername());
        recycle = findViewById(R.id.PRyc);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this);
        pcs = new Vector<>();
        getdata();


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
            Country.setText(countryName+","+cityName.split(",")[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recycle.setAdapter(postAdapter);
    }

    @Override
    protected void onRestart() {
        inti();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page2);

        inti();

        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePageActivity2.this, ProfileInfoModifyActivity2.class);
                startActivity(intent);
            }
        });

        ModifyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfilePageActivity2.this, ProfilePicsActivity2.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfilePageActivity2.this.finish();
            }
        });


    }

    void setavatar(){
        Httpservice.getinstance().setCtx(ProfilePageActivity2.this);
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/user/getcurrentuser", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject value = response.getJSONObject(0);
//                    Profile.setImageResource(Integer.parseInt(value.getString("avatar")));
                    switch (value.getString("avatar")){
                        case "1": Profile.setImageResource(R.drawable.photo1);
                            break;
                        case "2": Profile.setImageResource(R.drawable.photo2);
                            break;
                        case "3": Profile.setImageResource(R.drawable.photo3);
                            break;
                        case "4": Profile.setImageResource(R.drawable.photo4);
                            break;
                        case "5":
                            Profile.setImageResource(R.drawable.photo5);
                            break;
                        case "6": Profile.setImageResource(R.drawable.photo6);
                            break;
                        case "7": Profile.setImageResource(R.drawable.photo7);
                            break;
                        case "8": Profile.setImageResource(R.drawable.photo8);
                            break;
                        case "9": Profile.setImageResource(R.drawable.photo9);
                            break;
                        case "10": Profile.setImageResource(R.drawable.photo10);
                            break;
                        case "11": Profile.setImageResource(R.drawable.photo11);
                            break;
                        case "12":
                            Profile.setImageResource(R.drawable.photo12);
                            break;
                    }

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                inti();
            }
        }
    }

}