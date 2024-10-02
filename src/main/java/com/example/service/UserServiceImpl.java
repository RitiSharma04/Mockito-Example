package com.example.service;

import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repostiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existingUser.setName(user.getName());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(Math.toIntExact(id));
    }
}