package fr.hb.retrouvezmoi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.RetrofitApi;
import fr.hb.retrouvezmoi.models.Post;
import fr.hb.retrouvezmoi.services.PostRetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends AppCompatActivity {
    private Post post;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView imPostImage = findViewById(R.id.im_detail_post_image);
        TextView tvName =  findViewById(R.id.tv_detail_post_title);
        TextView tvDescription = findViewById(R.id.tv_detail_post_description);
        TextView tvPersonName = findViewById(R.id.tv_detail_post_name);
        TextView tvPersonFirstname = findViewById(R.id.tv_detail_post_firstname);
        TextView tvPersonEmail = findViewById(R.id.tv_detail_post_email);
        TextView tvPersonAddress = findViewById(R.id.tv_detail_post_address);
        TextView tvPersonPhoneNumber = findViewById(R.id.tv_detail_post_phone);
        TextView tvDate = findViewById(R.id.tv_detail_post_date);


        Intent intent = getIntent();
        if (intent.hasExtra(PostListActivity.KEY_BUNDLE_POST_ID)) {
            Long postId = intent.getLongExtra(PostListActivity.KEY_BUNDLE_POST_ID, 0);
            PostRetrofitService postService = RetrofitApi.getInstance();   //APPEL RESEAUX
            Call<Post> call = postService.fetchPostById(postId);   //Obtenir un seul post par iD
            //post = intent.getParcelableExtra(PostListActivity.KEY_BUNDLE_POST);

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {

                    Post post = response.body();
                    imPostImage.setImageBitmap(post.getPictureBase64());
                    tvName.setText(post.getTitle());
                    tvDescription.setText(post.getDescription());
                    tvPersonName.setText(post.getLastname());
                    tvPersonFirstname.setText(post.getFirstname());
                    tvPersonEmail.setText(post.getEmail());
                    tvPersonAddress.setText(post.getAddress());
                    tvPersonPhoneNumber.setText(post.getPhoneNumber());
                    tvDate.setText(post.getCreatedDate().toString());
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content), "Une erreur est survenue", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
                case R.id.action_share:
                    shareBy(post);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void shareBy(Post post){

    };
}