package fr.hb.retrouvezmoi.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.RetrofitApi;
import fr.hb.retrouvezmoi.models.CivilityEnum;
import fr.hb.retrouvezmoi.models.Post;
import fr.hb.retrouvezmoi.services.PostRetrofitService;
import fr.hb.retrouvezmoi.services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "CreateActivity";
    private Button buttonAddPost;
    private Button buttonAddPicture;
    private PostService postService ;
    private static final int REQUEST_CODE_PICK_PICTURE = 1000;
    private static final int REQUEST_CODE_TAKE_PICTURE = 2000;
    private ImageView imageViewPicture;
    private Bitmap selectedBitmap;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void onPickPictureFromGallery(View view) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        imageViewPicture = findViewById(R.id.imageViewPicture);
        buttonAddPicture = findViewById(R.id.pictures_add_btn);
        buttonAddPicture.setOnClickListener(view -> selectPicture(CreateActivity.this));
        buttonAddPost = findViewById(R.id.create_submit_btn);
        buttonAddPost.setOnClickListener(view -> {
            TextInputLayout titleInput = findViewById(R.id.title_input);
            String title = titleInput.getEditText().getText().toString();

            TextInputLayout descriptionInput = findViewById(R.id.description_input);
            String description = descriptionInput.getEditText().getText().toString();

            TextInputLayout addressInput = findViewById(R.id.adress_input);
            String address = addressInput.getEditText().getText().toString();

            TextInputLayout lastnameInput = findViewById(R.id.lastname_input);
            String lastname = lastnameInput.getEditText().getText().toString();

            TextInputLayout firstnameInput = findViewById(R.id.firstname_input);
            String firstname = firstnameInput.getEditText().getText().toString();

            TextInputLayout emailInput = findViewById(R.id.email_input);
            String email = emailInput.getEditText().getText().toString();

            TextInputLayout phoneInput = findViewById(R.id.phone_input);
            String phoneNumber = phoneInput.getEditText().getText().toString();
            RadioGroup radioGroupCivility = findViewById(R.id.genre);


            CivilityEnum civility = CivilityEnum.MR;
            int radioButtonChecked = radioGroupCivility.getCheckedRadioButtonId();
            switch (radioButtonChecked) {
                case R.id.mal:
                    civility = CivilityEnum.MR;
                    break;
                case R.id.female:
                    civility = CivilityEnum.MRS;
                    break;
            }

            //String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
            //title, String description,  CivilityEnum civility, String lastname,String firstname, String address,String email, String phoneNumber

            //String title, String description, String lastname, String firstname, String address, CivilityEnum civility, String email,
            // String phoneNumber, Date createdDate, String pictureBase64
            Post newPost = new Post(title, description,  civility, lastname, firstname, address,   email, phoneNumber);
            newPost.setPictureBase64(selectedBitmap);
            //Post newPost = postService.createPost(title,description,address,lastname, firstname,email,phoneNumber,radiovalue);
            PostRetrofitService postService = RetrofitApi.getInstance();
            Call<Post> createPostCall = postService.createPost(newPost);
            createPostCall.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.post_created_successfully, Snackbar.LENGTH_SHORT);
                    snackbar.addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                finish();
                            }
                        }
                    });
                    snackbar.show();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content), "Une erreur est survenue", Snackbar.LENGTH_SHORT).show();
                }
            });

        });
    }

    /*     onActivitityResult */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case REQUEST_CODE_PICK_PICTURE:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedPictureUri = data.getData();
                        imageViewPicture.setImageURI(selectedPictureUri);
                        try {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedPictureUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                            selectedBitmap = null;
                        }
                    }
                    break;

                case REQUEST_CODE_TAKE_PICTURE:
                    if (resultCode == RESULT_OK && data != null) {
                        selectedBitmap = (Bitmap) data.getExtras().get("data");
                        imageViewPicture.setImageBitmap(selectedBitmap);
                    }
                    break;
            }
        }
    }
////////////////////////// Add pictures /////////////////////////////////////

    private void selectPicture(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.picker_picture_title);

        builder.setItems(R.array.picker_picture_choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                switch (item) {
                    case 0:
                        takePicture();
                        break;
                    case 1:
                        pickPictureFromGallery();
                        break;
                    case 2:
                        dialogInterface.cancel();
                        break;
                }
            }
        });
        builder.show();
    }

    private void pickPictureFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE);
    }

    private void takePicture(){
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CODE_TAKE_PICTURE);
    }
}
