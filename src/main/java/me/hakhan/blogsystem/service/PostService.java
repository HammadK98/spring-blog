package me.hakhan.blogsystem.service;

import me.hakhan.blogsystem.entity.Post;
import me.hakhan.blogsystem.entity.User;

import java.util.List;

public interface PostService {
    public List<Post> findAll();
    public Post findById(int theId);
    public void save(Post thePost);
    public void deleteById(int theId);
    public List<Post> findByUser(User theUser);
}
