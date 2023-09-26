package com.nagarro.notesapp.impl;

import com.nagarro.notesapp.models.User;
import com.nagarro.notesapp.repository.UserRepository;
import com.nagarro.notesapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        User local = this.userRepository.getUserByEmail(user.getEmail());

        if (local != null) {
            System.out.println("User exists");
            throw new Exception("User already present!");
        } else {
            local = this.userRepository.save(user);
            return local;
        }
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User showUser(String email) {
        return this.userRepository.getUserByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

}
