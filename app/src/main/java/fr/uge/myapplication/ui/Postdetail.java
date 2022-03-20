package fr.uge.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import fr.uge.myapplication.R;

public class Postdetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);
        TextView auth = findViewById(R.id.authordetail);
        TextView content = findViewById(R.id.contentdetail);
        TextView nblikes = findViewById(R.id.nblike);
        TextView nbdislikes = findViewById(R.id.nbdislike);
        TextView nbcomments = findViewById(R.id.nbcomment);

        ImageButton Ilike = findViewById(R.id.like);
        ImageButton Inotlike = findViewById(R.id.dislike);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,R.layout.listcommentview);
        ListView listv = findViewById(R.id.listcomment);
        listv.setAdapter(stringArrayAdapter);

    }
}