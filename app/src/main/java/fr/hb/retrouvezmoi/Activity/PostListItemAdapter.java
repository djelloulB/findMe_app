package fr.hb.retrouvezmoi.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.List;
import fr.hb.retrouvezmoi.R;
import fr.hb.retrouvezmoi.models.Post;

public class PostListItemAdapter extends RecyclerView.Adapter<PostListItemAdapter.ViewHolder>{
    private static final String TAG = "MyActivity";
    private WeakReference<OnItemClickListener> listener;
    private List<Post> postList;

    private int recyclerItemRes;

    private Context context;


    public PostListItemAdapter(Context context, @LayoutRes int recyclerItemRes, List<Post> postList,OnItemClickListener listener) {
        this.recyclerItemRes = recyclerItemRes;
        this.context = context;
        this.postList = postList;
        this.listener = new WeakReference<>(listener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView versionName;
        private TextView description;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            versionName = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.card_description);
            imageView = itemView.findViewById(R.id.post_list_iv_picture);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(recyclerItemRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = postList.get(position);

        holder.versionName.setText(post.getTitle());
        holder.description.setText(post.getDescription());
        if (post.getPictureBase64() != null) {
            holder.imageView.setImageBitmap(post.getPictureBase64());
        }
        //SimpleDateFormat dateFormat = new SimpleDateFormat((post.getCreatedDate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listener.get() != null){
                    listener.get().onClick(post);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
    public interface OnItemClickListener {
        void onClick(Post postClicked);
    }

}
