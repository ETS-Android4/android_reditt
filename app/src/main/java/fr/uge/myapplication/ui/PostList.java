package fr.uge.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.model.Sru;
import fr.uge.myapplication.service.AuthService;
import fr.uge.myapplication.service.network.Httpservice;
import fr.uge.myapplication.ui.profile.ProfilePageActivity2;

public class PostList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

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
                        pc.setPos(value.getString("pos"));
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
        NavigationView navigationView = findViewById(R.id.navigationa);
        navigationView.setNavigationItemSelectedListener(this);
        init();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nouveaupost) {
            Intent intent2 = new Intent(PostList.this, fr.uge.myapplication.ui.newPost.class);
            startActivity(intent2);

        } else
            if (id == R.id.logout) {
            Httpservice.getinstance().setCtx(PostList.this);
            AuthService s = new AuthService(Httpservice.getinstance());
            s.logout(new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Httpservice.getinstance().setUsername("notconnected");
                    Httpservice.getinstance().setCokkie("none");
                    PostList.this.finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }else if(id == R.id.myprofil){
            startActivity(new Intent(PostList.this, ProfilePageActivity2.class));
        }
        return true;
    }
}