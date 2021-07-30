package com.vladbudan.restapp.repository;

import com.vladbudan.restapp.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatRepository {

    Cat save(Cat cat);

    Cat update(Cat cat);

    void delete(Integer id);

    Optional<Cat> getCatById(Integer id);

    List<Cat> getAllCat();
}
