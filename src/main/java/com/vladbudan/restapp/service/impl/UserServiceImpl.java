package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.UserRepository;
import com.vladbudan.restapp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.getAllUser();
    }
}
