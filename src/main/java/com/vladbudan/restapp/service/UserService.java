package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    User update(User user);

    User delete(Integer id);

    Optional<User> getById(Integer id);

    List<User> getAllUsers();
}
