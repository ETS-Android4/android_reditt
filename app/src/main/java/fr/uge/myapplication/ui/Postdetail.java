package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

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


public class Postdetail extends AppCompatActivity {

    TextView auth;
    TextView title;
    TextView content;
    TextView datet;
    TextView nblikes;
    TextView nbdislikes;
    TextView nbcomments;
    ImageButton Ilike;
    ImageButton Inotlike;
    RecyclerView recycle;
    CommentaireAdapter adapter;
    List<PC> list = new Vector<>();
    TextInputEditText addcom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);
        auth = findViewById(R.id.authordetail);
        title = findViewById(R.id.titledetail);
        content = findViewById(R.id.contentdetail);
        datet = findViewById(R.id.datedetail);
        nblikes = findViewById(R.id.nblike);
        nbdislikes = findViewById(R.id.nbdislike);
        nbcomments = findViewById(R.id.nbcomment);
        recycle = findViewById(R.id.listcomment);
        addcom = findViewById(R.id.addcommentinput);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentaireAdapter(this);

        auth.setText("");
        title.setText("");
        content.setText("");
        datet.setText("");
        nblikes.setText("");
        nbdislikes.setText("");
        nbcomments.setText(0+"");

        Ilike = findViewById(R.id.like);
        Inotlike = findViewById(R.id.dislike);
        getdata();

        Ilike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recycle.setAdapter(adapter);

    }

    void getdata(){
        long id = getIntent().getLongExtra("id",0);
        Httpservice.getinstance().setCtx(Postdetail.this);
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/post/"+id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray  response) {
                    try {
                        JSONObject value = response.getJSONObject(0);
                        PC pc = new PC();
                        pc.setId(Long.parseLong(value.getString("id")));
                        title.setText(value.getString("title"));
                        content.setText(value.getString("content"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        String dateInString = value.getString("date").split("\\.")[0];
                        Date date = formatter.parse(dateInString);
                        datet.setText(date.toString());
                        auth.setText(value.getJSONObject("author").getString("name"));
                        nblikes.setText(value.getString("upVote"));
                        nbdislikes.setText(value.getString("downVote"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Httpservice.getinstance().getJsonArray("http://192.168.1.114:8080/post/seeUnderComs/" + id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0;i<response.length();i++){
                        try {
                            JSONObject value = response.getJSONObject(i);
                            PC pc = new PC();
                            pc.setId(Long.parseLong(value.getString("id")));
                            pc.setContent(value.getString("content"));
                            System.out.println(value);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            String dateInString = value.getString("date").split("\\.")[0];
                            Date date = formatter.parse(dateInString);
                            pc.setDate(date);
                            Sru user = new Sru();
                            user.setName(value.getJSONObject("author").getString("name"));
                            pc.setAuthor(user);
                            list.add(pc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //nbcomments.setText(list.size());
                }catch (Exception e){

                }
                adapter.setList(list);
                System.out.println(response.length());
                nbcomments.setText(response.length()+"");
                adapter.refresh();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}