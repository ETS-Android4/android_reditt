package fr.uge.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileInfoModifyActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button Accept, ModifyProfile;
    private EditText Username;
    private EditText Currentpass;
    private EditText Newpass;
    private ImageView Return;
    private Spinner Spinner1;
    public String name;
    public ImageView Profile;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info_modify2);

        Accept = findViewById(R.id.accept);
        Username = findViewById(R.id.username);
        Return = findViewById(R.id.returnback);
        Currentpass = findViewById(R.id.currentpass);
        Newpass = findViewById(R.id.newpass);
        Spinner1 = findViewById(R.id.spinner);
        ModifyProfile = findViewById(R.id.modifyprofile);
        Profile = findViewById(R.id.profile);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(adapter);
        Spinner1.setOnItemSelectedListener(this);


        a = getIntent().getIntExtra("image", 0);
        if ( a != 0) {
            Profile.setImageResource(a);
        }




        Accept.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (Username.getText().toString().equals("") && Currentpass.getText().toString().equals("") && Newpass.getText().toString().equals("")) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "You need to fill informations", Toast.LENGTH_SHORT).show();
                }else if (Username.length() >= 1 && Username.length() < 6 ) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "Username is lower try another", Toast.LENGTH_SHORT).show();
                }else if (Currentpass.getText().toString().equals("") && Newpass.getText().toString().equals("")) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "type your current and new passwords", Toast.LENGTH_SHORT).show();
                }else if (Currentpass.getText().toString().equals("")) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "you need to type your Current Password", Toast.LENGTH_SHORT).show();
                }else if (Currentpass.length() >= 1 && Currentpass.length() < 6 ) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "Your Password is weak Try another", Toast.LENGTH_SHORT).show();
                }else if (Newpass.getText().toString().equals("")) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "type your New Password", Toast.LENGTH_SHORT).show();
                }else if (Newpass.length() >= 1 && Newpass.length() < 6 ) {
                    Toast.makeText(ProfileInfoModifyActivity2.this, "Your New Password is weak it should be more than 5 caracters", Toast.LENGTH_SHORT).show();
                }else if (Newpass.length() >= 6 && Currentpass.length() >= 6) {
                    if (Newpass.getText().toString().equals(Currentpass.getText().toString())) {
                        Toast.makeText(ProfileInfoModifyActivity2.this, "Your new password is the same of the current one", Toast.LENGTH_SHORT).show();
                    }else {
                        int i = 0;
                        String usename = Username.getText().toString();

                        Toast.makeText(ProfileInfoModifyActivity2.this, "Informations changed successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileInfoModifyActivity2.this, MainActivity.class);
                        intent.putExtra("keyname", usename);
                        intent.putExtra("countryname", name);
                        intent.putExtra("image", a);
                        startActivity(intent);


                    }
                }

            }
        });



        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usename = Username.getText().toString();

                Intent intent = new Intent(ProfileInfoModifyActivity2.this, MainActivity.class);
                intent.putExtra("image", a);
                intent.putExtra("keyname", usename);
                intent.putExtra("countryname", name);
                startActivity(intent);
            }
        });

    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        name = text;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}