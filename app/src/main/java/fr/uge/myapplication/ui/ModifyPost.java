package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.service.network.Httpservice;

public class ModifyPost extends AppCompatActivity {

    TextInputEditText title;
    TextInputEditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_post);
        title = findViewById(R.id.modifytitlePost);
        content = findViewById(R.id.modifycontentpost);
        getdata();
        Button b = findViewById(R.id.modifypostbtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject js = new JSONObject();
                long id = getIntent().getLongExtra("id",0);
                try {
                    js.put("id",id);
                    js.put("title",title.getEditableText().toString());
                    js.put("content",content.getEditableText().toString());
                    Httpservice.getinstance().postRequest("http://192.168.1.114:8080/user/modifyPost", js, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.split(",")[0].equals("200")){
                                ModifyPost.this.finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ModifyPost.this,"can't creat post",Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                }
            }
        });

    }

    void getdata(){
        long id = getIntent().getLongExtra("id",0);
        Httpservice.getinstance().setCtx(ModifyPost.this);
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/post/"+id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray  response) {
                try {
                    JSONObject value = response.getJSONObject(0);
                    PC pc = new PC();
                    pc.setId(Long.parseLong(value.getString("id")));
                    title.setText(value.getString("title"));
                    content.setText(value.getString("content"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}