package fr.hb.retrouvezmoi;

import fr.hb.retrouvezmoi.services.PostRetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static PostRetrofitService postRetrofitService;

    public static PostRetrofitService getInstance() {
        if (postRetrofitService == null) {
            synchronized (PostRetrofitService.class) {
                createApiBuilder();
            }
        }
        return postRetrofitService;
    }

    private static void createApiBuilder() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postRetrofitService = retrofit.create(PostRetrofitService.class);
    }
}
