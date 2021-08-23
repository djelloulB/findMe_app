package fr.hb.retrouvezmoi.services;

import java.util.List;

import fr.hb.retrouvezmoi.models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostRetrofitService {
    @GET("posts")
    Call<List<Post>> fetchAllPosts();

    @GET("posts/{id}")
    Call<Post> fetchPostById(@Path("id") Long id);

    @POST("posts")
    Call<Post> createPost(@Body Post user);




}
