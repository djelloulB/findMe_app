package fr.hb.retrouvezmoi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.models.CivilityEnum;
import fr.hb.retrouvezmoi.models.Post;
import fr.hb.retrouvezmoi.services.PostService;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "CreateActivity";
    private Button buttonAddPost;
    private PostService postService ;

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
        setContentView(R.layout.activity_create);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        buttonAddPost = findViewById(R.id.create_submit_btn);
        buttonAddPost.setOnClickListener(new View.OnClickListener(){
        //Intent i = new Intent(CreateActivity.this, PostListActivity.class);
        //startActivity(i);


            @Override
            public void onClick(View view){
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
                Post newPost = new Post(title, description, civility, lastname, firstname, address, email, phoneNumber);
                //Post newPost = postService.createPost(title,description,address,lastname, firstname,email,phoneNumber,radiovalue);
                PostService postService = PostService.getInstance();
                postService.getAllPost();
                Log.d(TAG,newPost.toString());
                postService.createPost(newPost);

                Log.d(TAG,newPost.toString());

                Snackbar snackbar = Snackbar.make(view, R.string.post_created_successfully, Snackbar.LENGTH_SHORT);
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
        });

    }

}
