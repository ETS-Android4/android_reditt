package fr.uge.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.uge.myapplication.R;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.Httpservice;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText username = findViewById(R.id.inputUsername);
        EditText email = findViewById(R.id.inputEmail);
        EditText passwd = findViewById(R.id.inputPassword);
        EditText passwd2 = findViewById(R.id.inputConformPassword);
        Button regiosterbtn = findViewById(R.id.btnRegister);

        regiosterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwd.getText().toString().equals(passwd2.getText().toString())){
                    AuthService auth = new AuthService(Httpservice.getinstance());
                    auth.signup(username.getText().toString(), passwd.getText().toString(), email.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.split(",")[0].equals("200")){
                                startActivity(new Intent(RegisterActivity.this,Loginpage.class));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this,"password not matched",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
