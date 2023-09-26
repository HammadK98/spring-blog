package me.hakhan.blogsystem.service;

import me.hakhan.blogsystem.dao.UserRepository;
import me.hakhan.blogsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User findByUsername(String theUsername) {
        return userRepository.findUserByUsername(theUsername);
    }

    @Override
    public User findByEmail(String theEmail) {
        return userRepository.findUserByEmail(theEmail);
    }

    @Override
    @Transactional
    public void save(User theUser) {
        theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
    }

    @Override
    public List<User> findAllByUsername() {
        return userRepository.findAllByOrderByUsernameAsc();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User theUser = userRepository.findUserByUsername(s);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ANONYMOUS"));
        return new org.springframework.security.core.userdetails.User(theUser.getUsername(), theUser.getPassword(), grantedAuthoritySet);
    }
}
