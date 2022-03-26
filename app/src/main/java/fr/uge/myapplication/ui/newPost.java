package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import fr.uge.myapplication.R;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.Httpservice;

public class newPost extends AppCompatActivity {

    TextInputEditText title;
    TextInputEditText content;
    Httpservice http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        title = findViewById(R.id.newtitlePost);
        content = findViewById(R.id.newcontentpost);
        http =Httpservice.getinstance();
        http.setCtx(newPost.this);
        //login();
        Button b = findViewById(R.id.createPost);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject js = new JSONObject();
                final boolean[] good = {false};
                try {
                    js.put("title",title.getEditableText().toString());
                    js.put("content",content.getEditableText().toString());
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

    void login(){
        AuthService auth = new AuthService(http);
        auth.login("Ilyass", "test789", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.split(",")[0].equals("200")){
                    System.out.println(response.split(",").length);
                    System.out.println(response.split(",")[0]);
                    try {

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