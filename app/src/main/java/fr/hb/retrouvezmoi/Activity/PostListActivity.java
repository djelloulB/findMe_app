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

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.RetrofitApi;
import fr.hb.retrouvezmoi.models.Post;
import fr.hb.retrouvezmoi.services.PostRetrofitService;
import fr.hb.retrouvezmoi.services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    //get data
    //private PostService postService = PostService.getInstance();
    //private List<Post> postList = postService.getAllPost();
    public static final String KEY_BUNDLE_POST_ID = "KEY_BUNDLE_POST_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        initToolbar();



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
                intent.putExtra(KEY_BUNDLE_POST_ID, postClicked.getId());
                startActivity(intent);

            }
        };

        //create and using adapter //create and using adapter / Context context, @LayoutRes int recyclerItemRes, List<Post> postList,OnItemClickListener listener
        //PostListItemAdapter postListItemAdapter = new PostListItemAdapter(this, R.layout.post_list_item, postList, clickListener);
        //recyclerView.setAdapter(postListItemAdapter);


////////////////////////// Retrofit Activity /////////////////////////////
        PostRetrofitService postService = RetrofitApi.getInstance();
        Call<List<Post>> call = postService.fetchAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // Create and using adapter
                PostListItemAdapter postRvAdapter = new PostListItemAdapter(PostListActivity.this, R.layout.post_list_item,response.body(), clickListener);
                recyclerView.setAdapter(postRvAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Snackbar.make(findViewById(android.R.id.content), "Une erreur est survenue", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void initToolbar() {
        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}