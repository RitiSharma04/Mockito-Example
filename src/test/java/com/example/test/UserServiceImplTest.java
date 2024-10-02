package com.example.test;

import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repostiory.UserRepository;
import com.example.service.UserServiceImpl;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;
    @Mock
    User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void getAllUsers() {
        // Mocking UserRepository behavior
        User user1 = new User(1, "John");
        User user2 = new User(2, "Alice");
        List<User> userList = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userServiceImpl.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Alice", result.get(1).getName());
    }



    @Test

    void getUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User result = userServiceImpl.getUserById(1);

        // Assert
        assertNotNull(result);
//        assertEquals(Optional.of(user),userServiceImpl.getUserById(1L));
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(2));

    }


    @Test
    void createUser() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user.getName(), userServiceImpl.createUser(user).getName());
    }


    @Test
    void updateUser() {
        // Arrange
        User existingUser = new User(1, "John");
        User updatedUser = new User(1, "Alice");
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        assertEquals("Alice",userServiceImpl.updateUser(1, updatedUser).getName());

    }



    @Test
    void deleteUser() {

    }

}