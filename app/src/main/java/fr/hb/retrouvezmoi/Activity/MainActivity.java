package fr.hb.retrouvezmoi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.hb.retrouvezmoi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createPostBtn = findViewById(R.id.main_activity_create_btn);
        createPostBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, CreateActivity.class);
            MainActivity.this.startActivity(i);
        });

        Button postListBtn = findViewById(R.id.main_activity_post_list_btn);
        postListBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, PostListActivity.class);
            startActivity(i);
       });

    }
}