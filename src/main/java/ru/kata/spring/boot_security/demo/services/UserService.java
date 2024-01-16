package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User getById(Long id);
    User findUserByUsername(String username);

    void save(User user);

    void deleteById(Long id);

    void update(User user);
    void createAdmin(String username, String password);
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}