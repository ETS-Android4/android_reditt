package fr.uge.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.uge.myapplication.R;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.Httpservice;

public class Loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = findViewById(R.id.username);
        EditText passwd = findViewById(R.id.password);
        Button loginb = findViewById(R.id.loginbtn);

        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Httpservice.getinstance().setCtx(Loginpage.this);
                AuthService auth = new AuthService(Httpservice.getinstance());
                auth.login(username.getText().toString(), passwd.getText().toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.split(",")[0].equals("200")){

                            try {
                                Httpservice.getinstance().setUsername(username.getText().toString());
                                System.out.println(response.split(",")[2]);
                                Httpservice.getinstance().setCokkie(response.split(",")[2]);
                            }catch (Exception e){

                            }
                            if(username.getText().equals("admin")){

//                                Intent intent = new Intent(Loginpage.this,PostList.class);
//                                startActivity(intent);
                            }else {

                                Intent intent = new Intent(Loginpage.this,newPost.class);
                                startActivity(intent);
                            }


                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }

        });
    }
}