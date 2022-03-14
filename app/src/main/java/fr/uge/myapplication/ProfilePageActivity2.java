package fr.uge.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

public class ProfilePageActivity2 extends AppCompatActivity {


    private TextView Name;
    private TextView Name1;
    private Button Modify,ModifyProfile;
    private TextView Date;
    private TextView Country;
    private ImageView Profile;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        Name1 = findViewById(R.id.name1);
        Modify = findViewById(R.id.modifyprofile);
        Date = findViewById(R.id.date);
        Country = findViewById(R.id.country);
        Profile = findViewById(R.id.profile);
        ModifyProfile = findViewById(R.id.modifyPhoto);
        a = getIntent().getIntExtra("image", 0);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        Date.setText(currentDate);



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


        String username = getIntent().getStringExtra("keyname");
        if (username != null) {
            Name.setText(username);
        }

        String username1 = getIntent().getStringExtra("keyname");
        if (username1 != null) {
            Name1.setText("u/@" + username1 + " - ");
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