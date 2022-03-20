package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Vector;

import fr.uge.myapplication.R;
import fr.uge.myapplication.model.PC;

public class PostList extends AppCompatActivity  {

    RecyclerView recycle;
    Button newPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        recycle = findViewById(R.id.Ryc);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        PostAdapter postAdapter = new PostAdapter(this);
        postAdapter.setItemClickListener(new PostAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(PostList.this,Postdetail.class);
                //intent.putExtra()
                startActivity(intent);
            }
        });
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
}