package me.hakhan.blogsystem.dao;


import me.hakhan.blogsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUsername(String theUsername);
    public User findUserByEmail(String theEmail);
    public List<User> findAllByOrderByUsernameAsc();
}
