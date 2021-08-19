package fr.hb.retrouvezmoi.dao;



import java.util.ArrayList;
import java.util.List;

import fr.hb.retrouvezmoi.models.CivilityEnum;
import fr.hb.retrouvezmoi.models.Post;

public final class PostDao {
    private static PostDao S_INSTANCE;

    private List<Post> data;

    private PostDao() {
        // avoid instanciate
        this.data = new ArrayList<>();

        data.add(new Post(
                "Perte CB",
                "J'ai perdu ma CB à la cantine ce midi, j'etais assis à la table près de la sortie",
                CivilityEnum.MR,
                "Boris",
                "SAUVAGE",
                "60 rue priol et laporte, Fréjus 83600",
                "sauvageboris.pro@gmail.com",
                "0680883962"
        )
        );
    }

    public static PostDao getInstance() {
        if (S_INSTANCE == null) {
            S_INSTANCE = new PostDao();
        }
        return S_INSTANCE;
    }

    public boolean create(Post post) {
        return data.add(post);
    }

    public List<Post> findAll() {
        return data;
    }

    public boolean remove(Post post) {
        return data.remove(post);
    }
}
