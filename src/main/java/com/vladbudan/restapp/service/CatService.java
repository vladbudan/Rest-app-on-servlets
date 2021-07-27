package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.User;

import java.util.List;

public interface CatService {

    void save(Cat cat);
    void update(Cat cat);
    void delete(Integer id );
    Cat getById(Integer id);
}
