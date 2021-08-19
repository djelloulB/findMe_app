package fr.hb.retrouvezmoi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.models.Post;
import fr.hb.retrouvezmoi.services.PostService;

public class PostListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    //get data
    private PostService postService = PostService.getInstance();
    private List<Post> postList = postService.getAllPost();
    public static final String KEY_BUNDLE_POST = "KEY_BUNDLE_POST";
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        // Get RecyclerView
        recyclerView = findViewById(R.id.activity_post_list);

        //Configure Adapter and LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        PostListItemAdapter.OnItemClickListener clickListener = new PostListItemAdapter.OnItemClickListener(){

            @Override
            public void onClick(Post postClicked) {

                //Toast.makeText(PostListActivity.this,"Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PostListActivity.this, DetailPostActivity.class);
                intent.putExtra(KEY_BUNDLE_POST, postClicked);
                startActivity(intent);

            }
        };

        //create and using adapter //create and using adapter / Context context, @LayoutRes int recyclerItemRes, List<Post> postList,OnItemClickListener listener
        PostListItemAdapter postListItemAdapter = new PostListItemAdapter(this, R.layout.post_list_item, postList, clickListener);
        recyclerView.setAdapter(postListItemAdapter);


    }
}