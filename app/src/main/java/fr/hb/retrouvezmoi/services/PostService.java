package fr.hb.retrouvezmoi.services;

import java.util.List;

import fr.hb.retrouvezmoi.dao.PostDao;
import fr.hb.retrouvezmoi.models.Post;

public final class PostService {
    private static PostService S_INSTANCE;

    private PostService() {
        // avoid instanciate
    }

    public static PostService getInstance() {
        if (S_INSTANCE == null) {
            S_INSTANCE = new PostService();
        }
        return S_INSTANCE;
    }

    public boolean createPost(Post post) {
        return PostDao.getInstance().create(post);
    }

    public List<Post> getAllPost() {
        return PostDao.getInstance().findAll();
    }
}
