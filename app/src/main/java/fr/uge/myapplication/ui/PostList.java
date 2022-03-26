package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.model.Sru;
import fr.uge.myapplication.service.network.Httpservice;

public class PostList extends AppCompatActivity  {

    RecyclerView recycle;
    FloatingActionButton newPost;
    PostAdapter postAdapter;
    List<PC> pcs;

    public void getdata(){
        Httpservice.getinstance().setCtx(PostList.this);
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray  response) {
                for (int i = 0;i<response.length();i++){
                    try {
                        JSONObject value = response.getJSONObject(i);
                        PC pc = new PC();
                        pc.setId(Long.parseLong(value.getString("id")));
                        pc.setTitle(value.getString("title"));
                        pc.setContent(value.getString("content"));

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        String dateInString = value.getString("date").split("\\.")[0];
                        Date date = formatter.parse(dateInString);
                        pc.setDate(date);
                        Sru user = new Sru();
                        user.setName(value.getJSONObject("author").getString("name"));
                        pc.setAuthor(user);
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
    protected void onRestart() {
        System.out.println("getdata()");
        init();
        super.onRestart();
    }
    void init(){
        recycle = findViewById(R.id.Ryc);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this);
        pcs = new Vector<>();
        getdata();
        recycle.setAdapter(postAdapter);
        newPost = findViewById(R.id.createNewPost);
        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(PostList.this, fr.uge.myapplication.ui.newPost.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        init();
    }
}