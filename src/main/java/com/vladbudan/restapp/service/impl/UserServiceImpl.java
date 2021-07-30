package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.exception.ResourceNotFoundException;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.UserRepository;
import com.vladbudan.restapp.service.UserService;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {


        return userRepository.update(user);
    }

    @Override
    public User delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public Optional<User> getById(Integer id) {

        Optional<User> optionalUser = userRepository.getById(id);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User with such id not found!");
        }

        return userRepository.getById(id);

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.getAllUsers();

        if (users == null) {
            throw new ResourceNotFoundException("Users failed!");
        }

        return userRepository.getAllUsers();

    }

}
