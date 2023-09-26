package me.hakhan.blogsystem.dao;

import me.hakhan.blogsystem.entity.Post;
import me.hakhan.blogsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findAllByOrderByDateDesc();
    public List<Post> findAllByAuthorOrderByDateDesc(User theUser);
}
