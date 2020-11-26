package hu.dreamteam.lux.service;

import hu.dreamteam.lux.entity.Post;
import hu.dreamteam.lux.entity.User;
import hu.dreamteam.lux.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private PostRepo postRepo;

    @Autowired
    private void setPostRepo(PostRepo postRepo){
        this.postRepo = postRepo;
    }

    public List<Post> getPosts(){
        List<Post> posts = postRepo.findAll();
        Collections.sort(posts, Collections.reverseOrder());
        return posts;
    }

    public void savePost(Post post, User user){
        post.setUser(user);
        post.setDate(new Date());
        postRepo.save(post);
    }

}
