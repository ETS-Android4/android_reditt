package fr.uge.myapplication.ui.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.uge.myapplication.MainActivity;
import fr.uge.myapplication.R;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.Httpservice;

public class ProfileInfoModifyActivity2 extends AppCompatActivity  {

    private Button Accept, ModifyProfile;
    private EditText validate;
    private EditText Currentpass;
    private EditText Newpass;
    private ImageView Return;
    public String name;
    public ImageView Profile;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info_modify2);

        Accept = findViewById(R.id.accept);
        validate = findViewById(R.id.repeatpass);
        Return = findViewById(R.id.returnback);
        Currentpass = findViewById(R.id.currentpass);
        Newpass = findViewById(R.id.newpass);
        ModifyProfile = findViewById(R.id.modifyprofile);
        Profile = findViewById(R.id.profile);

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Newpass.getText().toString().equals(validate.getText().toString())){
                    Httpservice.getinstance().setCtx(ProfileInfoModifyActivity2.this);
                    AuthService auth = new AuthService(Httpservice.getinstance());
                    auth.modifypasswd(Currentpass.getText().toString(), Newpass.getText().toString(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.split(",")[0].equals("200")){
                                        ProfileInfoModifyActivity2.this.finish();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                }else {
                    Toast.makeText(ProfileInfoModifyActivity2.this,"password invalide",Toast.LENGTH_LONG).show();
                }
            }
        });

    }






}