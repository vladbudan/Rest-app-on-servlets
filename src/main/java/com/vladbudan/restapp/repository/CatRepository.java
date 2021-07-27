package com.vladbudan.restapp.repository;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.User;

import java.util.List;

public interface CatRepository {

    Cat save(Cat cat);
    void update(Cat cat);
    void delete(Integer id );
    Cat getById(Integer id);
    List<Cat> getAllCat();
}
