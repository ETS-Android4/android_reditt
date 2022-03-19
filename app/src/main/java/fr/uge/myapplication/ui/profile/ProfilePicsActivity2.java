package fr.uge.myapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Vector;

import fr.uge.myapplication.MainActivity;
import fr.uge.myapplication.R;

public class ProfilePicsActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter  recyclerViewAdapter;
    ImageView imageView;


    int []arr = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo4, R.drawable.photo5, R.drawable.photo6,
            R.drawable.photo7, R.drawable.photo8, R.drawable.photo9,
            R.drawable.photo10, R.drawable.photo11, R.drawable.photo12};

    Vector<Integer> f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pics2);

        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(arr, new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(ProfilePicsActivity2.this, MainActivity.class);
                intent.putExtra("image", pos);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);




    }


}