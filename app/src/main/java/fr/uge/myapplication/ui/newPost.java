package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.uge.myapplication.R;

public class newPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Button b = findViewById(R.id.createPost);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPost.this.finish();
            }
        });
    }
}