package me.hakhan.blogsystem.service;

import me.hakhan.blogsystem.dao.PostRepository;
import me.hakhan.blogsystem.entity.Post;
import me.hakhan.blogsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> findAll() {
        return postRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Post findById(int theId) {
        Optional<Post> result = postRepository.findById(theId);
        Post thePost = null;
        if (result.isPresent()) {
            thePost = result.get();
        }

        return thePost;
    }

    @Override
    public void save(Post thePost) {
        postRepository.save(thePost);
    }

    @Override
    public void deleteById(int theId) {
        postRepository.deleteById(theId);
    }

    @Override
    public List<Post> findByUser(User theUser) {
        return postRepository.findAllByAuthorOrderByDateDesc(theUser);
    }
}
