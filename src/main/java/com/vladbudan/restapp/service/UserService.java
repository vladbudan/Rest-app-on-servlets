package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void update(User user);
    User delete(Integer id );
    User getById(Integer id);
    List<User> getAllUser();
}
