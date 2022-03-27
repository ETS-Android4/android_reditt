package fr.uge.myapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.model.Sru;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page2);

        Name = findViewById(R.id.name);
        Modify = findViewById(R.id.modifyprofile);
        Country = findViewById(R.id.pos);
        Profile = findViewById(R.id.profile);
        ModifyProfile = findViewById(R.id.modifyPhoto);
        back = findViewById(R.id.back);
        a = getIntent().getIntExtra("image", 0);
        Name.setText(Httpservice.getinstance().getUsername());
        recycle = findViewById(R.id.PRyc);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this);
        pcs = new Vector<>();
        getdata();




        recycle.setAdapter(postAdapter);

        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usename = Name.getText().toString();
                String countryname = Name.getText().toString();

                Intent intent = new Intent(ProfilePageActivity2.this, ProfileInfoModifyActivity2.class);
                intent.putExtra("image", a);
                intent.putExtra("keyname", usename);
                intent.putExtra("countryname", countryname);
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

        String username = getIntent().getStringExtra("keyname");
        if (username != null) {
            Name.setText(username);
        }

        String countryName = getIntent().getStringExtra("countryname");
        if (countryName != null) {
            Country.setText(countryName);
        }

        if ( a != 0) {
            Profile.setImageResource(a);
        }


    }
}